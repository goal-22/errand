package com.goal.errand.aspect;


import com.alibaba.fastjson.JSON;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.goal.errand.enums.AppEnums;
import com.goal.errand.utils.JWTUtil;
import com.goal.errand.utils.ResultHandle;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ：Goal
 * @date ：Created in 2022/8/12 16:30
 * @description：权限切面类
 */
@Aspect
@Component
public class AuthAdvice {

    private static Logger log = Logger.getLogger(AuthAdvice.class);

    @Pointcut("@annotation(com.goal.errand.annotation.Auth)")
    private void auth() {
    }

    @Around("auth()")
    public Object authBefore(ProceedingJoinPoint point) throws Throwable {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        //  获取请求头中的 header 属性
        String token = request.getHeader("token");
        if (token == null) {
            return JSON.toJSONString(new ResultHandle<String>().resultHandle(AppEnums.AUTH_FAIL.getCode(), AppEnums.AUTH_FAIL.getMsg(), null));
        }

        try {
            JWTUtil.verify(token);
        } catch (SignatureVerificationException e) {
            log.error("无效签名！ 错误 ->", e);
            return JSON.toJSONString(new ResultHandle<String>().resultHandle(AppEnums.SIG_VER.getCode(), AppEnums.SIG_VER.getMsg(), null));
        } catch (TokenExpiredException e) {
            log.error("token过期！ 错误 ->", e);
            return JSON.toJSONString(new ResultHandle<String>().resultHandle(AppEnums.TOKEN_EXPIRED.getCode(), AppEnums.TOKEN_EXPIRED.getMsg(), null));
        } catch (AlgorithmMismatchException e) {
            log.error("token算法不一致！ 错误 ->", e);
            return JSON.toJSONString(new ResultHandle<String>().resultHandle(AppEnums.TOKEN_ALG_MIS.getCode(), AppEnums.TOKEN_ALG_MIS.getMsg(), null));
        } catch (Exception e) {
            log.error("token无效！ 错误 ->", e);
            return JSON.toJSONString(new ResultHandle<String>().resultHandle(AppEnums.TOKEN_ERROR.getCode(), AppEnums.TOKEN_ERROR.getMsg(), null));
        }
        return point.proceed();
    }

}

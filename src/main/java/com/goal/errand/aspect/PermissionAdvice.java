package com.goal.errand.aspect;

import com.goal.errand.consts.AppConsts;
import com.goal.errand.resp.RestResp;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

/**
 * @author ：Goal
 * @date ：Created in 2022/8/12 16:30
 * @description：权限切面类
 */
@Aspect
@Component
@Order(1)
public class PermissionAdvice {

    @Pointcut("@annotation(com.goal.errand.annotation.PermissionAnn)")
    private void permissionCheck(){}

    @Around("permissionCheck()")
    public Object permissionCheckBefore(ProceedingJoinPoint point) throws Throwable {
        Object[] args = point.getArgs();
        String openId = (String) args[0];
        HttpSession session = (HttpSession) args[1];
        if (session.getAttribute(AppConsts.USER_OPEN_ID + openId) != null){
            Object proceed = point.proceed();
            System.out.println(proceed);
            return proceed;
        }
        return null;
    }

}

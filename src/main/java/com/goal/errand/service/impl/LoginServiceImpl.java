package com.goal.errand.service.impl;

import com.alibaba.fastjson.JSON;
import com.goal.errand.consts.AppConsts;
import com.goal.errand.enums.AppEnums;
import com.goal.errand.resp.RestResp;
import com.goal.errand.service.LoginService;
import com.goal.errand.utils.JWTUtil;
import com.goal.errand.utils.ResultHandle;
import com.goal.errand.utils.UUIDUtil;
import com.goal.errand.vo.WeChatLoginVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.concurrent.TimeUnit;


/**
 * @author ：Goal
 * @date ：Created in 2022/8/12 12:26
 * @description：登陆服务实现类
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Value("${wechat.config.app-id}")
    private String appId;
    @Value("${wechat.config.app-secret}")
    private String appSecret;
    Logger log = Logger.getLogger(LoginService.class);
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;


    @Override
    public RestResp<String> weChatLogin(String code) {
        log.debug(code);
        ResultHandle<String> resultHandle = new ResultHandle<>();
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appId + "&secret=" + appSecret + "&js_code=" + code + "&grant_type=authorization_code";
        RestResp<String> resp = null;
        try {
            //构建一个Client
            HttpClient client = HttpClientBuilder.create().build();
            //构建一个GET请求
            HttpGet get = new HttpGet(url);
            //提交GET请求
            HttpResponse response = client.execute(get);
            //拿到返回的HttpResponse的"实体"
            HttpEntity result = response.getEntity();
            String content = EntityUtils.toString(result);
            if (StringUtils.isEmpty(content)){
                return resultHandle.resultHandle(AppEnums.FAIL.getCode(),AppEnums.FAIL.getMsg(),null);
            }
            WeChatLoginVo contenObj = JSON.parseObject(content, WeChatLoginVo.class);
            WeChatLoginVo weChatLoginVo = contenObj;
            String token = JWTUtil.getToken(weChatLoginVo);
            log.debug(weChatLoginVo.getOpenId());
            log.debug(weChatLoginVo.getSessionKey());
            log.debug(token);
            resp = resultHandle.resultHandle(AppEnums.SUCCESS.getCode(), AppEnums.SUCCESS.getMsg(), token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }
}

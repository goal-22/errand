package com.goal.errand.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.goal.errand.vo.WeChatLoginVo;
import org.apache.commons.lang3.StringUtils;

import java.util.Calendar;
import java.util.HashMap;

/**
 * @author ：Goal
 * @date ：Created in 2022/9/17 17:31
 * @description：Jwt（生成/验证）工具类
 */
public class JWTUtil {

    private final static String SECRET = "@##G%OAL";

    /**
     * 获取token
     *
     * @param  wxUser
     * @return token
     */
    public static String getToken(WeChatLoginVo wxUser) {
        // 指定token过期时间为3天
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 3);

        String token = JWT.create()
                // Header
                .withHeader(new HashMap<>())
                // Payload
                .withClaim("openId", wxUser.getOpenId())
                .withClaim("sessionKey", wxUser.getSessionKey())
                // 过期时间
                .withExpiresAt(calendar.getTime())
                // 签名用的secret
                .sign(Algorithm.HMAC256(SECRET.getBytes()));
        return token;
    }

    /**
     * 获取token
     *
     * @param  openId
     * @param  sessionKey
     * @return token
     */
    public static String getToken(String openId,String sessionKey){
        // 指定token过期时间为3天
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 3);

        String token = JWT.create()
                // Header
                .withHeader(new HashMap<>())
                // Payload
                .withClaim("openId", openId)
                .withClaim("sessionKey", sessionKey)
                // 过期时间
                .withExpiresAt(calendar.getTime())
                // 签名用的secret
                .sign(Algorithm.HMAC256(SECRET.getBytes()));
        return token;
    }

    /**
     * 验证token合法性 成功返回token
     */
    public static DecodedJWT verify(String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        // 创建解析对象，使用的算法和secret要与创建token时保持一致
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SECRET.getBytes())).build();
        // 解析指定的token
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        return decodedJWT;
    }
}


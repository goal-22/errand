package com.goal.errand;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.HashMap;

/**
 * @author ：Goal
 * @date ：Created in 2022/9/17 18:33
 * @description：test
 */
public class Test {

    public static String getToken(){
        // 指定token过期时间为10秒
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, 10);

        String token = JWT.create()
                .withHeader(new HashMap<>())  // Header
                .withClaim("openId", 21)  // Payload
                .withClaim("sessionKey", "baobao")
                .withExpiresAt(calendar.getTime())  // 过期时间
                .sign(Algorithm.HMAC256("@##G%OAL"));  // 签名用的secret

        return token;
    }

    public static void decodeToken(String token){
        // 创建解析对象，使用的算法和secret要与创建token时保持一致
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("@##G%OAL")).build();
        // 解析指定的token
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        // 获取解析后的token中的payload信息
        Claim openId = decodedJWT.getClaim("openId");
        Claim sessionKey = decodedJWT.getClaim("sessionKey");
        System.out.println(openId);
        System.out.println(sessionKey);
    }

    public static void main(String[] args) {
//        String token = getToken();
        decodeToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJvcGVuSWQiOiJvb1BOTTVLUWZHVnlqS3NHN3phSTdzTXJuZ2V3Iiwic2Vzc2lvbklkIjoiNTBVZ215TTdCRHFid3FsSTZTNC91QT09IiwiZXhwIjoxNjYzNjY5NDE2fQ.r4aSQUQgVPBeq9smnUuCHfJDW1KxDKzGit31YQWNIuM");
    }
}

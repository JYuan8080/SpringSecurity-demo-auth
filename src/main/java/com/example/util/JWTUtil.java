package com.example.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.text.ParseException;

/**
 * @author JYuan
 * @create 2021-08-19 18:33
 */
public class JWTUtil {
    private static final String SECRET_KEY = "ipbre/fc";

    public static String getJWT(String username) throws  ParseException {

        /*
            create() 用于创建 jwt
            withHeader() 用于设置header，需要一个map作为参数，通常不进行设置，推荐使用默认
            withClaim() 用于设置Payload，需要key-value作为参数
            withExpiresAt() 用于指定令牌的过期时间
            sign() 用于设置签名，常用的算法就是 Algorithm.HMAC256("密钥")
         */

        return JWT.create()
                .withClaim("username", username)
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    public static String verify(String token) {
        // 创建jwt的验证对象，需要将密钥传递
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET_KEY)).build();
        // 验证token，过期报TokenExpiredException，签名不对报SignatureVerificationException
        DecodedJWT jwt = verifier.verify(token);
        // 获取payload中自定义的username
        return  jwt.getClaim("username").asString();
    }


}

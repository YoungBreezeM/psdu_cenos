package com.fw.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fw.domain.User;

import java.util.Date;

/**
 * @author yqf
 */
public class TokenUtil {

    private static final long EXPIRE_TIME = 15 * 60 * 1000;
    private static final String TOKEN_SECRET = "token123";


    /**
     * 签名生成
     * @param user
     * @return token
     */
    public static String sign(Object o) {
        String token = null;
        try {
            Date expireAt = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            token = JWT.create()
                    .withIssuer("auth0")
                    .withClaim("object",o.toString())
                    .withExpiresAt(expireAt)
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));
        } catch (IllegalArgumentException | JWTCreationException je) {

            throw new RuntimeException(je);
        }
        return token;
    }


    public static Boolean verify(String token){

        try {
            JWTVerifier jwtVerifier=JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("auth0").build();
            DecodedJWT decodedJwt =jwtVerifier.verify(token);
            System.out.println("认证通过：");
            System.out.println("object: " + decodedJwt .getClaim("object").asString());
            System.out.println("过期时间：      " + decodedJwt .getExpiresAt());
        } catch (IllegalArgumentException | JWTVerificationException e) {
            //抛出错误即为验证不通过
            return false;
        }
        return true;
    }

}


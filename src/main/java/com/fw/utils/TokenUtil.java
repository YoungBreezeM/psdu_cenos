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

    private static final long EXPIRE_TIME = 15 * 60 * 1000*60;
    private static final String TOKEN_SECRET = "token123";


    /**
     * 签名生成
     * @param user
     * @return token
     */
    public static String sign(User user) {
        String token = null;
        try {
            Date expireAt = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            token = JWT.create()
                    .withIssuer("auth0")
                    .withClaim("account",user.getAccount())
                    .withClaim("password",user.getPassword())
                    .withClaim("role",user.getRole())
                    .withExpiresAt(expireAt)
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));
        } catch (IllegalArgumentException | JWTCreationException je) {

            throw new RuntimeException(je);
        }
        return token;
    }


    public static User verify(String token){

        try {
            JWTVerifier jwtVerifier=JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("auth0").build();
            DecodedJWT decodedJwt =jwtVerifier.verify(token);
            System.out.println("认证通过：");
            System.out.println("object: " + decodedJwt .getClaim("account").asString());
            System.out.println("过期时间：      " + decodedJwt .getExpiresAt());
            return  new User(
                    decodedJwt .getClaim("account").asString(),
                    decodedJwt .getClaim("password").asString(),
                    decodedJwt .getClaim("role").asString()
            );
        } catch (IllegalArgumentException | JWTVerificationException e) {
            //抛出错误即为验证不通过
            return null;
        }
    }

}


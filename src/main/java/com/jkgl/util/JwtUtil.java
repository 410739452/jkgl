package com.jkgl.util;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtUtil {

	// 过期时间
	private static final long EXPIRE_TIME = 5 * 60 * 1000;

	/**
	 * 校验token是否正确
	 * 
	 * @param token  密钥
	 * @param secret 用户的密码
	 * @return 是否正确
	 */
	public static boolean verify(String token, String userid, String secret) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			JWTVerifier verifier = JWT.require(algorithm).withClaim("userid", userid).build();
			verifier.verify(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 获得token中的信息无需secret解密也能获得
	 * 
	 * @return token中包含的用户id
	 */
	public static String getUserid(String token) {
		try {
			DecodedJWT jwt = JWT.decode(token);
			return jwt.getClaim("userid").asString();
		} catch (JWTDecodeException e) {
			return null;
		}
	}

	/**
	 * 生成签名
	 * 
	 * @param userid 用户id
	 * @param secret   用户的密码
	 * @return 加密的token
	 */
	public static String sign(String userid, String secret) {
		Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
		Algorithm algorithm = Algorithm.HMAC512(secret);
		return JWT.create().withClaim("userid", userid).withExpiresAt(date).sign(algorithm);
	}
}

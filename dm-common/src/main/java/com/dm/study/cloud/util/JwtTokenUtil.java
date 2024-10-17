package com.dm.study.cloud.util;

import com.dm.study.cloud.constant.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2021年05月29日 13:47</p>
 * <p>类全名：com.dm.system.util.JwtTokenUtil</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Component
public class JwtTokenUtil {
	/**
	 * 生成令牌
	 * @param username 用户名
	 * @return 令牌
	 */
	public String generateToken(String username) {
		Map<String,Object> claims = new HashMap<>(2);
		claims.put(Claims.SUBJECT, username);
		claims.put(Claims.ISSUED_AT, new Date());
		return generateToken(claims);
	}

	/**
	 * 从数据声明生成令牌
	 * @param claims 数据声明
	 * @return 令牌
	 */
	private String generateToken(Map<String,Object> claims) {
		Date expirationDate = new Date(System.currentTimeMillis() + Constants.TOKEN_EXPIRATION_TIME);
		return Jwts.builder().setClaims(claims).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, Constants.TOKEN_SECRET_KEY).compact();
	}

	/**
	 * 判断令牌是否过期
	 * @param token 令牌
	 * @return 过期返回true
	 */
	public Boolean isTokenExpired(String token) {
		Claims claims = getClaimsFromToken(token);
		Date expiration = claims.getExpiration();
		return expiration.before(new Date());
	}

	/**
	 * 从令牌中获取数据声明
	 * @param token 令牌
	 * @return 数据声明
	 */
	private Claims getClaimsFromToken(String token) {
		// DefaultJwtParser在转换claims的时候就已经抛出ExpiredJwtException，所以特殊处理一下
		// return Jwts.parser().setSigningKey(SystemConstants.TOKEN_SECRET_KEY).parseClaimsJws(token).getBody();
		Claims claims;
		try {
			claims = Jwts.parser().setSigningKey(Constants.TOKEN_SECRET_KEY) // 设置标识名
					.parseClaimsJws(token)  //解析token
					.getBody();
		} catch (ExpiredJwtException e) {
			claims = e.getClaims();
		}
		return claims;
	}

	/**
	 * 从令牌中获取用户名
	 * @param token 令牌
	 * @return 用户名
	 */
	public String getUsernameFromToken(String token) {
		String username = null;
		try {
			Claims claims = getClaimsFromToken(token);
			System.out.println("claims = " + claims.toString());
			username = claims.getSubject();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("e = " + e.getMessage());
		}
		return username;
	}

	/**
	 * 刷新令牌
	 * @param token 原令牌
	 * @return 新令牌
	 */
	public String refreshToken(String token) {
		String refreshedToken;
		try {
			Claims claims = getClaimsFromToken(token);
			claims.put(Claims.ISSUED_AT, new Date());
			refreshedToken = generateToken(claims);
		} catch (Exception e) {
			refreshedToken = null;
		}
		return refreshedToken;
	}
}

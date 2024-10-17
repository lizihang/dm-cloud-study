package com.dm.study.cloud.filter;

import com.dm.study.cloud.constant.Constants;
import com.dm.study.cloud.util.JwtTokenUtil;
import com.dm.study.cloud.util.RedisUtil;
import com.dm.study.cloud.util.ServerHttpUtil;
import com.dm.study.cloud.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.net.URI;
import java.util.concurrent.TimeUnit;
/**
 * <p>标题：校验token</p>
 * <p>功能：自定义全局过滤器</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年05月02日 19:38</p>
 * <p>类全名：com.dm.study.cloud.filter.JwtAuthenticationTokenFilter</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Component
public class JwtAuthenticationTokenFilter implements GlobalFilter, Ordered {
	private final Logger logger = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);
	@Resource
	JwtTokenUtil jwtTokenUtil;
	@Resource
	RedisUtil    redisUtil;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		logger.info("校验token全局filter");
		ServerHttpRequest request = exchange.getRequest();
		ServerHttpResponse response = exchange.getResponse();
		// 不校验token的url
		URI uri = request.getURI();
		if (uri.getPath().contains("/login")) {
			return chain.filter(exchange);
		}
		String token = ServerHttpUtil.getToken(exchange);
		String username = jwtTokenUtil.getUsernameFromToken(token);
		if (token == null || username == null) {
			return ServerHttpUtil.render(response, Result.error(HttpStatus.UNAUTHORIZED.value(), "token已过期，请重新登录！"));
		}
		if (redisUtil.exist(Constants.USER_KEY + username)) {
			redisUtil.setExpireTime(Constants.USER_KEY + username, 30, TimeUnit.MINUTES);
		} else {
			return ServerHttpUtil.render(response, Result.error(HttpStatus.UNAUTHORIZED.value(), "token已过期，请重新登录！"));
		}
		return chain.filter(exchange);
	}

	@Override
	public int getOrder() {
		return HIGHEST_PRECEDENCE;
	}
}
package com.dm.study.cloud.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
/**
 * <p>标题：时间记录过滤器</p>
 * <p>功能：自定义全局过滤器</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年05月02日 19:38</p>
 * <p>类全名：com.dm.study.cloud.filter.TimeFilter</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Component
public class TimeFilter implements GlobalFilter, Ordered {
	private static final Logger logger = LoggerFactory.getLogger(TimeFilter.class);

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		ServerHttpRequest request = exchange.getRequest();
		URI uri = request.getURI();
		logger.info("请求地址：{}", uri);
		long start = System.currentTimeMillis();
		logger.info("start:{}", start);
		return chain.filter(exchange).then(Mono.fromRunnable(() -> {
			long end = System.currentTimeMillis();
			logger.info("end:{}", end);
			logger.info("业务处理时间：{}ms", (end - start));
		}));
	}

	@Override
	public int getOrder() {
		return HIGHEST_PRECEDENCE + 1;
	}
}

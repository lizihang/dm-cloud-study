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

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR;

/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年03月16日 13:58</p>
 * <p>类全名：com.dm.study.cloud.filter.TestFilter</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Component
public class TestFilter implements GlobalFilter, Ordered {
    private static final Logger logger = LoggerFactory.getLogger(TestFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String uri = request.getURI().toString();
        logger.info("uri:{}", uri);
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}

package com.dm.study.cloud.filter;

import com.dm.study.cloud.feign.ToUserService;
import com.dm.study.cloud.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2024年01月24日 11:13</p>
 * <p>类全名：com.dm.study.cloud.filter.ExecutorDemoFilter</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Component
public class ExecutorDemoFilter implements GlobalFilter, Ordered {
	private final Logger logger = LoggerFactory.getLogger(ExecutorDemoFilter.class);
	@Resource
	ToUserService toUserService;
	//
	ExecutorService executorService = Executors.newFixedThreadPool(1);

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		// WebFlux异步调用，同步会报错
		Future<Result> future = executorService.submit(() -> toUserService.testFeign());
		Result result;
		try {
			result = future.get();
			logger.info(result.toString());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		logger.info("远程调用示例filter");
		return chain.filter(exchange);
	}

	@Override
	public int getOrder() {
		return HIGHEST_PRECEDENCE + 2;
	}
}

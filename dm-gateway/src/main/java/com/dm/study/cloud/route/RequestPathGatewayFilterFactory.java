package com.dm.study.cloud.route;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
/**
 * <p>标题：请求路径过滤器</p>
 * <p>功能：自定义路由过滤器</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年05月02日 19:42</p>
 * <p>类全名：com.dm.study.cloud.route.RequestPathGatewayFilterFactory</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Component
public class RequestPathGatewayFilterFactory extends AbstractGatewayFilterFactory<RequestPathGatewayFilterFactory.Config> {
	private static final Logger logger = LoggerFactory.getLogger(RequestPathGatewayFilterFactory.class);

	public RequestPathGatewayFilterFactory() {
		super(Config.class);
	}

	@Override
	public GatewayFilter apply(Config config) {
		return (exchange, chain) -> {
			String path = exchange.getRequest().getPath().toString();
			logger.info("路由过滤器：本次请求地址：{}；配置的参数：[name={},path={}]", path, config.getName(), config.getPath());
			return chain.filter(exchange);
		};
	}

	/**
	 * 简化配置
	 * @return
	 */
	@Override
	public List<String> shortcutFieldOrder() {
		return Arrays.asList("name", "path");
	}

	public static class Config {
		private String name;
		private String path;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}
	}
}

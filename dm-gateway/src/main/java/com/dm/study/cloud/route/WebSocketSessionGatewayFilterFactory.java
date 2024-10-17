package com.dm.study.cloud.route;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2023年11月21日 17:01</p>
 * <p>类全名：com.dm.study.cloud.route.WebSocketSessionDecorator</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Component
public class WebSocketSessionGatewayFilterFactory extends AbstractGatewayFilterFactory<WebSocketSessionGatewayFilterFactory.Config> {
	private static final Logger logger = LoggerFactory.getLogger(WebSocketSessionGatewayFilterFactory.class);

	public WebSocketSessionGatewayFilterFactory() {
		super(WebSocketSessionGatewayFilterFactory.Config.class);
	}

	@Override
	public GatewayFilter apply(Config config) {
		return (exchange, chain) -> {
			// 设置WebSocket相关的头部信息，如Upgrade, Connection等
			HttpHeaders headers = new HttpHeaders();
			headers.set("Upgrade", "websocket");
			headers.set("Connection", "Upgrade");
			headers.set("UpgradeInsecureRequests", "1"); // 如果需要支持非安全连接，可以设置此头部信息
			exchange.getRequest().getHeaders().putAll(headers);
			// 继续处理请求链中的下一个过滤器或目标资源
			return chain.filter(exchange);
		};
	}

	public static class Config {
		private String targetUri;

		public String getTargetUri() {
			return targetUri;
		}

		public void setTargetUri(String targetUri) {
			this.targetUri = targetUri;
		}
	}
}

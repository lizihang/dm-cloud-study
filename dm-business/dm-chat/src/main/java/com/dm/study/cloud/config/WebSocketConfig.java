package com.dm.study.cloud.config;

import com.dm.study.cloud.handler.MyWebSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.RequestUpgradeStrategy;
import org.springframework.web.socket.server.jetty.JettyRequestUpgradeStrategy;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2023年11月22日 15:37</p>
 * <p>类全名：com.dm.study.cloud.config.WebSocketConfig</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(new MyWebSocketHandler(), "/wss")
				.setAllowedOrigins("*")
				.addInterceptors(new HttpSessionHandshakeInterceptor())
				.setHandshakeHandler(new DefaultHandshakeHandler())
				.withSockJS(); // 如果需要支持SockJS
	}

	@Bean
	public MyWebSocketHandler myWebSocketHandler() {
		return new MyWebSocketHandler();
	}

	@Bean
	public RequestUpgradeStrategy requestUpgradeStrategy() {
		return new JettyRequestUpgradeStrategy();
	}
}

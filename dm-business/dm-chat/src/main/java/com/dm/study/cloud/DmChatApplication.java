package com.dm.study.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2023年11月20日 10:51</p>
 * <p>类全名：com.dm.study.cloud.DmChatApplication</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication(exclude = PropertyPlaceholderAutoConfiguration.class)
public class DmChatApplication {
	public static void main(String[] args) {
		SpringApplication.run(DmChatApplication.class, args);
	}
}

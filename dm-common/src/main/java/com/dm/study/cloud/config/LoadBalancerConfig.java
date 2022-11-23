package com.dm.study.cloud.config;

import com.dm.study.cloud.core.CustomTestLoadBalancer;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.cloud.loadbalancer.core.ReactorServiceInstanceLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
/**
 * <p>标题：自定义负载均衡配置类</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年11月23日 12:00</p>
 * <p>类全名：com.dm.study.cloud.config.LoadBalancerConfig</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Configuration(proxyBeanMethods = false)
@LoadBalancerClients(defaultConfiguration = LoadBalancerConfig.class)
public class LoadBalancerConfig {
	@Bean
	public ReactorServiceInstanceLoadBalancer testLoadBalance(LoadBalancerClientFactory loadBalancerClientFactory, Environment environment) {
		String name = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
		return new CustomTestLoadBalancer(loadBalancerClientFactory.getLazyProvider(name, ServiceInstanceListSupplier.class), name);
	}
}

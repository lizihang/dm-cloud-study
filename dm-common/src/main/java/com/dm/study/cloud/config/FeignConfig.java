package com.dm.study.cloud.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
/**
 * <p>标题：Feign请求拦截器</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：解决Feign调用时，请求头丢失问题
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年11月23日 15:15</p>
 * <p>类全名：com.dm.study.cloud.config.FeignConfig</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Configuration
public class FeignConfig {
	@Bean
	public RequestInterceptor requestInterceptor() {
		return new RequestInterceptor() {
			@Override
			public void apply(RequestTemplate template) {
				// 获取原请求
				ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
				assert attributes != null;
				HttpServletRequest request = attributes.getRequest();
				// 获取原请求中携带的Cookie请求头
				String toServer = request.getHeader("toServer");
				// 将cookie 同步到新的请求的请求头中
				template.header("toServer", toServer);
			}
		};
	}
}

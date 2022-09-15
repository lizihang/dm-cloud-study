package com.dm.study.cloud.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
/**
 * <p>标题：自定义参数</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：nacos动态刷新配置参数
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年09月15日 13:44</p>
 * <p>类全名：com.dm.study.cloud.config.NacosProperties</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Component
@RefreshScope
public class MyNacosProperties {
	@Value("${useLocalCache:false}")
	private boolean useLocalCache;

	public boolean isUseLocalCache() {
		return useLocalCache;
	}
}

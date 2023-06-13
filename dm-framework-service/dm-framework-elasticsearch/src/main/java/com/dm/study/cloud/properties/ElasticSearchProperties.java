package com.dm.study.cloud.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
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
 * <p>创建日期：2023年06月13日 15:47</p>
 * <p>类全名：com.dm.study.cloud.properties.ElasticSearchProperties</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Component
@ConfigurationProperties(prefix = "dm.elasticsearch")
public class ElasticSearchProperties {
	private boolean enable;
	/**
	 * ip
	 */
	private String  ip;
	/**
	 * 端口
	 */
	private int     port;

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
}

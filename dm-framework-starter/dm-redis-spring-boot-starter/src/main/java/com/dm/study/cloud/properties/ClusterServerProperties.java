package com.dm.study.cloud.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年09月15日 17:47</p>
 * <p>类全名：com.dm.study.cloud.properties.ClusterServerProperties</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Component
@ConfigurationProperties(prefix = "dm.redisson.cluster")
public class ClusterServerProperties {
	/**
	 *
	 */
	private List<String> nodeAddresses;
	/**
	 * 密码
	 */
	private String       password;

	public List<String> getNodeAddresses() {
		return nodeAddresses;
	}

	public void setNodeAddresses(List<String> nodeAddresses) {
		this.nodeAddresses = nodeAddresses;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

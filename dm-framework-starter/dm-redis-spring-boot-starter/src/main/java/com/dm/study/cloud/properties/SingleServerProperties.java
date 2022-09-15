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
 * <p>创建日期：2022年09月15日 17:32</p>
 * <p>类全名：com.dm.study.cloud.properties.SingleServerProperties</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Component
@ConfigurationProperties(prefix = "dm.redisson.single")
public class SingleServerProperties {
	/**
	 * 地址(redis://127.0.0.1:6379)
	 */
	private String address;
	/**
	 * 库
	 */
	private int    database;
	/**
	 * 密码
	 */
	private String password;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getDatabase() {
		return database;
	}

	public void setDatabase(int database) {
		this.database = database;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

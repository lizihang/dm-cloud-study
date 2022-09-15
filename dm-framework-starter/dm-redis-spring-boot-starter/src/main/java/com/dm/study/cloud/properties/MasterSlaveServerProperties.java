package com.dm.study.cloud.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Set;
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
 * <p>类全名：com.dm.study.cloud.properties.MasterSlaveServerProperties</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Component
@ConfigurationProperties(prefix = "dm.redisson.master")
public class MasterSlaveServerProperties {
	/**
	 *
	 */
	private String      masterAddress;
	/**
	 *
	 */
	private Set<String> slaveAddresses;
	/**
	 * 库
	 */
	private int         database;
	/**
	 * 密码
	 */
	private String      password;

	public String getMasterAddress() {
		return masterAddress;
	}

	public void setMasterAddress(String masterAddress) {
		this.masterAddress = masterAddress;
	}

	public Set<String> getSlaveAddresses() {
		return slaveAddresses;
	}

	public void setSlaveAddresses(Set<String> slaveAddresses) {
		this.slaveAddresses = slaveAddresses;
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

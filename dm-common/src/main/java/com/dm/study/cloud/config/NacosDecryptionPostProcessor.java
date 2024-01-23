package com.dm.study.cloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2024年01月23日 14:07</p>
 * <p>类全名：com.dm.study.cloud.config.NacosDecryptionPostProcessor</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class NacosDecryptionPostProcessor implements EnvironmentPostProcessor {
	@Override
	public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
		// 在这里解密配置文件中的账号密码，并更新到propertySources中
		String encryptedUsername = environment.getProperty("spring.cloud.nacos.username");
		String encryptedPassword = environment.getProperty("spring.cloud.nacos.password");
		// 解密逻辑，例如使用加密算法解密
		String decryptedUsername = encryptedUsername;
		String decryptedPassword = encryptedPassword.substring(encryptedPassword.indexOf("-") + 1);
		// 更新解密后的值到environment中
		environment.getPropertySources().addFirst(new MyPropertySource("nacosCredentials", decryptedUsername, decryptedPassword));
	}

	private static class MyPropertySource extends PropertySource<Object> {
		private final String decryptedUsername;
		private final String decryptedPassword;

		public MyPropertySource(String name, String decryptedUsername, String decryptedPassword) {
			super(name);
			this.decryptedUsername = decryptedUsername;
			this.decryptedPassword = decryptedPassword;
		}

		@Override
		public Object getProperty(String name) {
			if ("spring.cloud.nacos.username".equals(name)) {
				return decryptedUsername;
			}
			if ("spring.cloud.nacos.password".equals(name)) {
				return decryptedPassword;
			}
			return null;
		}
	}
}

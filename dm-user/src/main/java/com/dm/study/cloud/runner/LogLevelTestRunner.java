package com.dm.study.cloud.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
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
 * <p>创建日期：2024年10月17日 13:56</p>
 * <p>类全名：com.dm.study.cloud.runner.LogLevelTestRunner</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Component
public class LogLevelTestRunner implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(LogLevelTestRunner.class);

	@Override
	public void run(String... args) throws Exception {
		while (true) {
			logger.info("info log");
			logger.debug("debug log");
			Thread.sleep(5000);
		}
	}
}

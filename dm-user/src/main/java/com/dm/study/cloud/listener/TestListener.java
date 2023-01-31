package com.dm.study.cloud.listener;

import com.dm.study.cloud.listener.event.TestEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * <p>创建日期：2023年01月30日 11:03</p>
 * <p>类全名：com.dm.study.cloud.listener.TestListener</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Component
public class TestListener extends AbstractListener<TestEvent> {
	private static final Logger logger = LoggerFactory.getLogger(TestListener.class);

	@Override
	void doListenerBusiness(TestEvent event) {
		logger.info("TestEvent,source is {}，username is {}", event.getSource(), event.getUsername());
	}
}

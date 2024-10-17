package com.dm.study.cloud.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
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
 * <p>创建日期：2023年04月26日 18:25</p>
 * <p>类全名：com.dm.study.cloud.listener.RedisKeyExpirationListener</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Component
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {
	private static final Logger logger = LoggerFactory.getLogger(RedisKeyExpirationListener.class);

	/**
	 * Creates new {@link MessageListener} for {@code __keyevent@*__:expired} messages.
	 * @param listenerContainer must not be {@literal null}.
	 */
	public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
		super(listenerContainer);
	}

	@Override
	protected void doHandleMessage(Message message) {
		// 过期key
		String expiredKey = message.toString();
		logger.info("key<{}>过期了", expiredKey);
	}
}

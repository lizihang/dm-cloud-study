package com.dm.study.cloud.listener.event;

import org.springframework.context.ApplicationEvent;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2023年01月31日 8:53</p>
 * <p>类全名：com.dm.study.cloud.listener.event.AbstractEvent</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public abstract class AbstractEvent extends ApplicationEvent {
	private static final long   serialVersionUID = -7230146460251582736L;
	// 自定义扩展属性
	private final        String username;

	public AbstractEvent(Object source, String username) {
		super(source);
		this.username = username;
	}

	public String getUsername() {
		return username;
	}
}

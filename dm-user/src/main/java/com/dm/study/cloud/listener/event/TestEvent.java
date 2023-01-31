package com.dm.study.cloud.listener.event;

/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2023年01月30日 11:02</p>
 * <p>类全名：com.dm.study.cloud.listener.event.TestEvent</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class TestEvent extends AbstractEvent {
	private static final long serialVersionUID = -8140279747094910373L;

	public TestEvent(Object source, String username) {
		super(source, username);
	}
}

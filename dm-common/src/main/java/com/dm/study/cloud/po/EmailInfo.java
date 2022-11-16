package com.dm.study.cloud.po;

import java.io.Serializable;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年11月16日 10:56</p>
 * <p>类全名：com.dm.study.cloud.po.EmailInfo</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class EmailInfo implements Serializable {
	private static final long   serialVersionUID = 8927020826059071816L;
	// 收件人
	private              String to;
	// 主题
	private              String subject;
	// 内容
	private              String content;

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}

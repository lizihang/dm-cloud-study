package com.dm.study.cloud.param;

import lombok.Getter;
import lombok.Setter;

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
 * <p>创建日期：2024年09月12日 11:31</p>
 * <p>类全名：com.dm.study.cloud.param.PublishParam</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Getter
@Setter
public class PublishParam implements Serializable {
	private static final long   serialVersionUID = 1511086157550339915L;
	private              String chainId;
	private              String json;
}

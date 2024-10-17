package com.dm.study.cloud.schema.node.data.common;

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
 * <p>创建日期：2024年09月12日 11:00</p>
 * <p>类全名：com.dm.study.cloud.schema.node.data.common.MetaSchema</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Getter
@Setter
public class MetaSchema implements Serializable {
	private static final long   serialVersionUID = 8243228065137721346L;
	private              String id;
	private              String ref_param_id;
}

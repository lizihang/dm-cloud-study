package com.dm.study.cloud.schema.node.meta;

import com.dm.study.cloud.schema.node.meta.handler.HandlerSchema;
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
 * <p>创建日期：2024年09月12日 11:12</p>
 * <p>类全名：com.dm.study.cloud.schema.node.meta.NodeMetaSchema</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Getter
@Setter
public class NodeMetaSchema implements Serializable {
	private static final long          serialVersionUID = 6112789107521654569L;
	private              HandlerSchema handlers;
}

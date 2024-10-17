package com.dm.study.cloud.schema.node.data.common;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2024年09月12日 10:59</p>
 * <p>类全名：com.dm.study.cloud.schema.node.data.common.ListSchema</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Getter
@Setter
public class ListSchema implements Serializable {
	private static final long               serialVersionUID = -8670500573464961819L;
	private              String             type;
	private              List<ObjectSchema> object_schema;
	private              ListSchema         list_schema;
}

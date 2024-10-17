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
 * <p>创建日期：2024年09月12日 10:56</p>
 * <p>类全名：com.dm.study.cloud.schema.node.data.common.ValueContentSchema</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Getter
@Setter
public class ValueContentSchema implements Serializable {
	private static final long   serialVersionUID = -497799110802304577L;
	private              String ref_node_id;
	private              String ref_var_name;
}

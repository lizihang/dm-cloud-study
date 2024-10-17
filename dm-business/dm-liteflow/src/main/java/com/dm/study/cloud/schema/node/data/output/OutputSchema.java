package com.dm.study.cloud.schema.node.data.output;

import com.dm.study.cloud.schema.node.data.common.ListSchema;
import com.dm.study.cloud.schema.node.data.common.MetaSchema;
import com.dm.study.cloud.schema.node.data.common.ObjectSchema;
import com.dm.study.cloud.schema.node.data.common.ValueSchema;
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
 * <p>创建日期：2024年09月12日 9:59</p>
 * <p>类全名：com.dm.study.cloud.schema.node.data.output.OutputSchema</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Getter
@Setter
public class OutputSchema implements Serializable {
	private static final long         serialVersionUID = 8673968152917878134L;
	private              String       name;
	private              String       type;
	private              String       desc;
	private              boolean      required;
	private              ValueSchema  value;
	private              ObjectSchema object_schema;
	private              ListSchema   list_schema;
	private              MetaSchema   meta;
}

package com.dm.study.cloud.schema.node.data;

import com.dm.study.cloud.schema.node.data.input.InputSchema;
import com.dm.study.cloud.schema.node.data.output.OutputSchema;
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
 * <p>创建日期：2024年09月12日 11:13</p>
 * <p>类全名：com.dm.study.cloud.schema.node.data.NodeDataSchema</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Getter
@Setter
public class NodeDataSchema implements Serializable {
	private static final long               serialVersionUID = 6933429468411308572L;
	private              List<InputSchema>  inputs;
	private              List<OutputSchema> outputs;
	private              Object     settings;
}

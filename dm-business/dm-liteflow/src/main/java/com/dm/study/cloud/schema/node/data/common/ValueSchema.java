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
 * <p>类全名：com.dm.study.cloud.schema.node.data.common.ValueSchema</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Getter
@Setter
public class ValueSchema implements Serializable {
	private static final long   serialVersionUID = -2423352188090501417L;
	private              String type;
	// 这里用Object，因为根据type不同，content类型不同，使用时，在程序中处理，ValueContentSchema可以在使用的时候转换
	private              Object content;
}

package com.dm.study.cloud.content;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2024年09月12日 9:55</p>
 * <p>类全名：com.dm.study.cloud.content.TestContentObj</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Getter
@Setter
public class TestContentObj implements Serializable {
	private static final long                     serialVersionUID = -6003549127606843881L;
	private              List<Map<String,Object>> input;
	private              List<Map<String,Object>> output;
	private              Map<String,Object>       settings;
}

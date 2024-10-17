package com.dm.study.cloud.content;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;
/**
 * <p>标题：liteflow自定义上下文对象</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2024年09月11日 15:14</p>
 * <p>类全名：com.dm.study.cloud.content.TestContent</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Getter
@Setter
public class TestContent implements Serializable {
	private static final long                           serialVersionUID = 5520445725562441770L;
	// 一个节点对应一个contentObj，map的key为节点tag
	private              Map<String,TestContentObj>     cmpContentMap;
	// 节点执行结果
	private              Map<String,Map<String,Object>> cmpResult;
}

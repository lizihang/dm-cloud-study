package com.dm.study.cloud.util;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.HashMap;
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
 * <p>创建日期：2023年05月10日 19:20</p>
 * <p>类全名：com.dm.study.cloud.util.SseMap</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class SseMap {
	public static final Map<String,SseEmitter> emitterMap = new HashMap<>();
}

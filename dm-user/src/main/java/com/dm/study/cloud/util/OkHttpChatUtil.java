package com.dm.study.cloud.util;

import com.alibaba.fastjson.JSON;
import com.dm.study.cloud.param.PromptParams;
import okhttp3.*;
import okhttp3.internal.sse.RealEventSource;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;

import java.util.HashMap;
import java.util.function.BiConsumer;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2023年05月11日 13:48</p>
 * <p>类全名：com.dm.study.cloud.util.OkHttpChatUtil</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class OkHttpChatUtil {
	public static void doChat(PromptParams chatParam, BiConsumer<String,String> onEvent) {
		// "http://vpn1.rendersea.com:18501/botong/llm/chat"
		OkHttpClient client = new OkHttpClient();
		HashMap<Object,Object> bodyParam = new HashMap<>();
		bodyParam.put("prompt", chatParam.getPrompt());
		bodyParam.put("stream", chatParam.getStream());
		String bodyStr = JSON.toJSONString(bodyParam);
		// 构造POST请求
		Request request = new Request.Builder() //
				.url(chatParam.getUrl()) //
				.post(RequestBody.create(bodyStr, MediaType.parse("application/json"))) //
				.build();
		// 事件监听
		RealEventSource eventSource = new RealEventSource(request, new EventSourceListener() {
			@Override
			public void onOpen(EventSource eventSource, Response response) {
				System.out.println("Connection opened");
			}

			@Override
			public void onClosed(EventSource eventSource) {
				System.out.println("Connection closed");
			}

			@Override
			public void onEvent(EventSource eventSource, String id, String type, String data) {
				System.out.println("Event received: " + data);
				onEvent.accept(type, data);
			}

			@Override
			public void onFailure(EventSource eventSource, Throwable t, Response response) {
				System.out.println("Error: " + t.getMessage());
			}
		});
		eventSource.connect(client);
	}
}

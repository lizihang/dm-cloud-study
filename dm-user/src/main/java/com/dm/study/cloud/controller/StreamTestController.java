package com.dm.study.cloud.controller;

import com.alibaba.fastjson.JSON;
import com.dm.study.cloud.param.PromptParams;
import com.dm.study.cloud.util.OkHttpChatUtil;
import com.dm.study.cloud.util.SseMap;
import com.dm.study.cloud.vo.Result;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.publisher.Flux;

import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
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
 * <p>创建日期：2023年05月10日 8:36</p>
 * <p>类全名：com.dm.study.cloud.controller.StreamTestController</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@CrossOrigin
@RestController
@RequestMapping("/stream")
public class StreamTestController {
	@PostMapping(value = "/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<ServerSentEvent<String>> streamEvents(@RequestBody PromptParams params) {
		Map<String,Object> result = new HashMap<>();
		result.put("finished", false);
		result.put("stream", params.getStream());
		result.put("delta", "hallow world");
		String[] arr = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j" };
		return Flux.fromArray(arr).map(str -> {
			result.put("delta", str);
			return ServerSentEvent.<String>builder() //
					.id(str) //
					.event("delta") //
					.comment(params.getPrompt()) //
					.data(JSON.toJSONString(result)) //
					.build();
		}).doOnEach(v -> {
			System.out.println("v:" + v);
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
	}

	@PostMapping(value = "/sse2", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<ServerSentEvent<String>> streamEvents2(@RequestBody String requestData) {
		Map<String,Object> result = new HashMap<>();
		result.put("finished", false);
		result.put("delta", "hallow world");
		return Flux.interval(Duration.ofSeconds(1)) //
				.map(sequence -> ServerSentEvent.<String>builder() //
						.id(String.valueOf(sequence)) //
						.event("delta") //
						.data(JSON.toJSONString(result)) //
						.build());
	}

	@GetMapping(value = "/subscribe", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public SseEmitter subscribe(String clientId) {
		// 默认30秒超时,设置为0L则永不超时
		SseEmitter sseEmitter = new SseEmitter(0L);
		sseEmitter.onTimeout(() -> SseMap.emitterMap.remove(clientId));
		sseEmitter.onCompletion(() -> System.out.println("完成！！！"));
		sseEmitter.onError(e -> {
			System.out.println("出错啦，错误信息为：" + e.getMessage());
		});
		SseMap.emitterMap.put(clientId, sseEmitter);
		System.out.println("当前emitter为：");
		SseMap.emitterMap.keySet().forEach(key -> System.out.println("key:" + key));
		return sseEmitter;
	}

	@RequestMapping(value = "/send")
	public Result send(String clientId) throws Exception {
		System.out.println("当前emitter为：");
		SseMap.emitterMap.keySet().forEach(key -> System.out.println("key:" + key));
		SseEmitter sseEmitter = SseMap.emitterMap.get(clientId);
		Map<String,Object> result = new HashMap<>();
		result.put("finished", false);
		result.put("delta", "hallow world");
		for (int i = 0; i < 10; i++) {
			sseEmitter.send( //
					SseEmitter.event() //
							.id("1") //
							.name("delta") //
							.data(JSON.toJSONString(result)) //
							.build());
			Thread.sleep(500);
		}
		return Result.success("成功！");
	}

	/**
	 * httpClient方式调用接口
	 * @param prompt
	 * @return
	 * @throws IOException
	 */
	@PostMapping("/testChat")
	public Result testChat(String prompt) throws IOException {
		HttpPost httpPost = new HttpPost("http://vpn1.rendersea.com:18501/botong/llm/chat");
		HashMap<Object,Object> bodyParam = new HashMap<>();
		bodyParam.put("prompt", prompt);
		bodyParam.put("stream", true);
		String bodyStr = JSON.toJSONString(bodyParam);
		StringEntity entity = new StringEntity(bodyStr, "UTF-8");
		entity.setContentEncoding("UTF-8");
		entity.setContentType("application/json");
		httpPost.setEntity(entity);
		httpPost.setHeader("accept", "text/event-stream");
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = httpClient.execute(httpPost);
		HttpEntity resEntity = response.getEntity();
		InputStream content = resEntity.getContent();
		byte[] b = new byte[1024];
		int len = 0;
		while ((len = content.read(b)) > 0) {
			System.out.println("=====start");
			System.out.println(new String(b, 0, len));
			System.out.println("=====end");
		}
		String resStr = EntityUtils.toString(resEntity, "UTF-8");
		System.out.println(resStr);
		return null;
	}

	/**
	 * 真正完整的chat
	 * @param params 参数
	 * @return
	 * @throws IOException
	 */
	@PostMapping("/streamChat")
	public Result streamChat(@Valid PromptParams params) {
		System.out.println("当前emitter为：");
		SseMap.emitterMap.keySet().forEach(key -> System.out.println("key:" + key));
		SseEmitter sseEmitter = SseMap.emitterMap.get(params.getId());
		if (sseEmitter == null) {
			return Result.error("异常！");
		}
		params.setUrl("http://vpn1.rendersea.com:18501/botong/llm/chat");
		OkHttpChatUtil.doChat(params, (type, message) -> {
			try {
				sseEmitter.send( //
						SseEmitter.event() //
								.name(type) //
								.data(message) //
								.build());
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		return Result.success("成功！");
	}
}

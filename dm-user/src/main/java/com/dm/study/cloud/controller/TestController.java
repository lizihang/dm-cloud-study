package com.dm.study.cloud.controller;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dm.study.cloud.config.MyNacosProperties;
import com.dm.study.cloud.exception.DmException;
import com.dm.study.cloud.feign.GoodsFeignService;
import com.dm.study.cloud.param.DmUserQueryParams;
import com.dm.study.cloud.po.SysUser;
import com.dm.study.cloud.po.TestGroupingBy;
import com.dm.study.cloud.service.SysUserService;
import com.dm.study.cloud.vo.MySearchResult;
import com.dm.study.cloud.vo.Result;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.zhipu.oapi.ClientV4;
import com.zhipu.oapi.Constants;
import com.zhipu.oapi.service.v4.model.*;
import com.zhipu.oapi.service.v4.tools.*;
import io.reactivex.Flowable;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
/**
 * <p>标题：测试用controller</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年03月15日 15:52</p>
 * <p>类全名：com.dm.study.cloud.controller.UserController</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@RestController
@RequestMapping("/test")
public class TestController {
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	@Resource
	SysUserService    userService;
	@Resource
	RedissonClient    client;
	@Resource
	GoodsFeignService goodsFeignService;
	@Resource
	MyNacosProperties myNacosProperties;
	@Value("${server.port}")
	int               port;

	@PostMapping("/testZhiPu")
	public Result testZhiPu(@RequestBody String content) {
		ClientV4 client = new ClientV4 //
				.Builder("ce14316ce3613150678422e6c2cbac63.MKxRHmtOeRAQAUXf") //
				.networkConfig(300, 300, 300, 300, TimeUnit.SECONDS) //
				.build();
		List<ChatMessage> messages = new ArrayList<>();
		ChatMessage chatMessage = new ChatMessage(ChatMessageRole.USER.value(), content);
		messages.add(chatMessage);
		String requestId = "test-" + System.currentTimeMillis();
		ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder() //
				.model(Constants.ModelChatGLM4) //
				.stream(Boolean.FALSE) //
				.invokeMethod(Constants.invokeMethod) //
				.messages(messages) //
				.requestId(requestId) //
				.build();
		ModelApiResponse invokeModelApiResp = client.invokeModelApi(chatCompletionRequest);
		System.out.println("model output:" + JSON.toJSONString(invokeModelApiResp));
		System.out.println(invokeModelApiResp.getMsg());
		return Result.success("查询成功！", invokeModelApiResp);
	}

	private static final ObjectMapper mapper = new ObjectMapper();

	public static ObjectMapper defaultObjectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
		return mapper;
	}

	@PostMapping("/testZhiPuStream")
	public SseEmitter testZhiPuStream(@RequestBody String content) throws JsonProcessingException {
		SseEmitter sseEmitter = new SseEmitter(1000 * 60 * 10L);
		ClientV4 client = new ClientV4 //
				.Builder("ce14316ce3613150678422e6c2cbac63.MKxRHmtOeRAQAUXf") //
				.networkConfig(300, 300, 300, 300, TimeUnit.SECONDS) //
				.build();
		List<ChatMessage> messages = new ArrayList<>();
		ChatMessage chatMessage = new ChatMessage(ChatMessageRole.USER.value(), content);
		messages.add(chatMessage);
		String requestId = "test-" + System.currentTimeMillis();
		ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder() //
				.model(Constants.ModelChatGLM4) //
				.stream(Boolean.TRUE) //
				.messages(messages) //
				.requestId(requestId) //
				.build();
		ModelApiResponse sseModelApiResp = client.invokeModelApi(chatCompletionRequest);
		if (sseModelApiResp.isSuccess()) {
			AtomicBoolean isFirst = new AtomicBoolean(true);
			List<Choice> choices = new ArrayList<>();
			ChatMessageAccumulator chatMessageAccumulator = mapStreamToAccumulator(sseModelApiResp.getFlowable()).doOnNext(accumulator -> {
				if (isFirst.getAndSet(false)) {
					System.out.print("Response: ");
				}
				if (accumulator.getDelta() != null && accumulator.getDelta().getTool_calls() != null) {
					String jsonString = mapper.writeValueAsString(accumulator.getDelta().getTool_calls());
					System.out.println("tool_calls: " + jsonString);
				}
				if (accumulator.getDelta() != null && accumulator.getDelta().getContent() != null) {
					System.out.print(accumulator.getDelta().getContent());
				}
				sseEmitter.send(SseEmitter.event().name("delta").data(accumulator.getDelta()));
				choices.add(accumulator.getChoice());
				if (accumulator.getChoice().getFinishReason() != null) {
					sseEmitter.complete();
				}
			}).doOnComplete(System.out::println).lastElement().blockingGet();
			// ModelData data = new ModelData();
			// data.setChoices(choices);
			// data.setUsage(chatMessageAccumulator.getUsage());
			// data.setId(chatMessageAccumulator.getId());
			// data.setCreated(chatMessageAccumulator.getCreated());
			// data.setRequestId(chatCompletionRequest.getRequestId());
			// sseModelApiResp.setFlowable(null);// 打印前置空
			// sseModelApiResp.setData(data);
		}
		// logger.info("model output: {}", mapper.writeValueAsString(sseModelApiResp));
		return sseEmitter;
	}

	@PostMapping("/webSearchZhiPu")
	public Result webSearchZhiPu(@RequestBody String content) throws JsonProcessingException {
		ClientV4 client = new ClientV4 //
				.Builder("ce14316ce3613150678422e6c2cbac63.MKxRHmtOeRAQAUXf") //
				.networkConfig(300, 300, 300, 300, TimeUnit.SECONDS) //
				.build();
		List<SearchChatMessage> messages = new ArrayList<>();
		SearchChatMessage chatMessage = new SearchChatMessage(ChatMessageRole.USER.value(), content);
		messages.add(chatMessage);
		String requestId = "test-" + System.currentTimeMillis();
		WebSearchParamsRequest chatCompletionRequest = WebSearchParamsRequest.builder() //
				.model("web-search-pro") //
				.stream(Boolean.FALSE) //
				.messages(messages) //
				.requestId(requestId) //
				.build();
		WebSearchApiResponse webSearchApiResponse = client.invokeWebSearchPro(chatCompletionRequest);
		List<WebSearchChoice> choices = webSearchApiResponse.getData().getChoices();
		if (choices != null && choices.size() > 0) {
			List<WebSearchMessageToolCall> toolCalls = choices.get(0).getMessage().getToolCalls();
			for (WebSearchMessageToolCall toolCall : toolCalls) {
				System.out.println(toolCall);
				if ("search_result".equals(toolCall.getType())){
					JSONObject jsonObject = JSON.parseObject(toolCall.toString());
					JSONArray searchResult = jsonObject.getJSONArray("search_result");
					List<MySearchResult> list = searchResult.toJavaList(MySearchResult.class);
					System.out.println("here");
				}

			}
		}
		logger.info("model output: {}", mapper.writeValueAsString(webSearchApiResponse));
		return Result.success("查询成功", webSearchApiResponse);
	}

	public static Flowable<ChatMessageAccumulator> mapStreamToAccumulator(Flowable<ModelData> flowable) {
		return flowable.map(chunk -> {
			return new ChatMessageAccumulator(chunk.getChoices().get(0).getDelta(), null, chunk.getChoices().get(0), chunk.getUsage(), chunk.getCreated(), chunk.getId());
		});
	}

	@GetMapping("/testFeign/queryGoods/{id}")
	public Result queryGoods(@PathVariable String id) {
		List<SysUser> list = userService.list(new LambdaQueryWrapper<SysUser>().eq(SysUser::getId, id));
		Result result = goodsFeignService.getGoods(id);
		if (result.getStatus() == 200) {
			Object data = result.getData();
			System.out.println(data.toString());
		}
		return Result.success("查询成功！", list);
	}

	@GetMapping("/testMyBatisInterceptor/{id}")
	public Result testMyBatisInterceptor(@PathVariable String id) {
		SysUser user = new SysUser();
		user.setUsername("测试拦截器");
		user.setNickname("test" + id);
		user.setPassword("12345");
		user.setStatus(1);
		user.setEmail("testUser@163.com");
		user.setGender(1);
		userService.save(user);
		return Result.success("保存成功！");
	}

	@GetMapping("/testGetInterceptor/{id}")
	public Result testGetInterceptor(@PathVariable String id) {
		QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
		List<String> emails = new ArrayList<>();
		emails.add("test@163.com");
		emails.add("testUser@163.com");
		wrapper.in("email", emails);
		List<SysUser> list = userService.list(wrapper);
		SysUser byId = userService.getById(id);
		return Result.success("查询成功！", list);
	}

	@GetMapping("/testGetInterceptor2/{id}")
	public Result testGetInterceptor2(@PathVariable String id) {
		DmUserQueryParams params = new DmUserQueryParams();
		params.setEmail("testUser@163.com");
		List<SysUser> list = userService.queryUserList(params);
		return Result.success("查询成功！", list);
	}

	@GetMapping("/testGetInterceptor3/{id}")
	public Result testGetInterceptor3(@PathVariable String id) {
		QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
		wrapper.in("email", "testUser@163.com", "test@163.com");
		wrapper.like("username", "测试");
		List<SysUser> list = userService.list(wrapper);
		return Result.success("查询成功！", list);
	}

	@GetMapping("/testGetInterceptor4/{id}")
	public Result testGetInterceptor4(@PathVariable String id) {
		LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
		wrapper.in(SysUser::getEmail, "testUser@163.com", "test@163.com");
		wrapper.like(SysUser::getUsername, "测试");
		List<SysUser> list = userService.list(wrapper);
		return Result.success("查询成功！", list);
	}

	@PostMapping("/testPostMethod")
	public Result testPostMethod(@RequestBody DmUserQueryParams param) {
		return Result.success(param.toString());
	}

	@PostMapping("/testPostMethod2")
	public Result testPostMethod2(DmUserQueryParams param) {
		return Result.success(param.toString());
	}

	@GetMapping("/testGetNacosProperties")
	public Result testGetNacosProperties() {
		return Result.success("查询成功！", myNacosProperties.isUseLocalCache());
	}

	@GetMapping("/testGroupingBy")
	public Result testGroupingBy() {
		List<SysUser> userList = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			SysUser user = new SysUser();
			user.setUsername("username" + i);
			user.setStatus(i % 3);
			userList.add(user);
		}
		Map<Integer,List<SysUser>> collect = userList.stream().collect(Collectors.groupingBy(SysUser::getStatus));
		TestGroupingBy test = new TestGroupingBy();
		test.setUserList1(collect.get(0));
		test.setUserList2(collect.get(1));
		test.setUserList3(collect.get(2));
		test.setAllList(userList);
		return Result.success("ok", test);
	}

	/**
	 * 测试全局异常处理
	 * @return
	 */
	@GetMapping("/testGlobalExceptionHandler")
	public Result testGlobalExceptionHandler() {
		throw new DmException("测试全局异常处理");
	}

	/**
	 * 测试自定义负载均衡
	 * @return
	 */
	@PostMapping("/queryPort")
	public Result queryPort() {
		return Result.success("本服务端口为：" + port);
	}

	// blockHandler 函数，原方法调用被限流/降级/系统保护的时候调用
	public Result handleException(BlockException ex) {
		return Result.success("ex:" + ex.toString());
	}

	// fallback 函数，原方法调用被降级的时候调用
	public Result fallback(Throwable e) {
		return Result.error(500, "ex:" + e.toString());
	}
}

package com.dm.study.cloud.util;

import com.alibaba.fastjson.JSON;
import com.dm.study.cloud.constant.Constants;
import com.dm.study.cloud.vo.Result;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2024年01月24日 11:32</p>
 * <p>类全名：com.dm.study.cloud.util.ServerHttpUtil</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class ServerHttpUtil {
	/**
	 * 渲染到客户端
	 * @param response 响应
	 * @param result 统一返回结果
	 * @return Mono
	 */
	public static Mono<Void> render(ServerHttpResponse response, Result result) {
		return render(response, HttpStatus.OK, result);
	}

	/**
	 * 渲染到客户端
	 * @param response 响应
	 * @param result 统一返回结果
	 * @return Mono
	 */
	public static Mono<Void> render(ServerHttpResponse response, HttpStatus status, Result result) {
		byte[] bits = JSON.toJSONString(result).getBytes(StandardCharsets.UTF_8);
		DataBuffer buffer = response.bufferFactory().wrap(bits);
		response.setStatusCode(status);
		// 指定编码，否则在浏览器中会中文乱码
		response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
		return response.writeWith(Mono.just(buffer));
	}

	/**
	 * 获取请求头中的参数值
	 * @param headName 请求头名称
	 * @return 请求头值
	 */
	public static String getRequestHead(ServerWebExchange exchange, String headName) {
		ServerHttpRequest request = exchange.getRequest();
		return request.getHeaders().getFirst(headName);
	}

	public static String getToken(ServerWebExchange exchange) {
		ServerHttpRequest request = exchange.getRequest();
		String token = request.getHeaders().getFirst(Constants.TOKEN_HEADER);
		if (StrUtil.isNotEmpty(token) && token.startsWith(Constants.TOKEN_PREFIX)) {
			token = token.replace(Constants.TOKEN_PREFIX, "");
		}
		return token;
	}
}

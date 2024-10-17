package com.dm.study.cloud.util;

import com.alibaba.fastjson.JSON;
import com.dm.study.cloud.constant.Constants;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年05月04日 20:11</p>
 * <p>类全名：com.dm.study.cloud.util.ServletUtil</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class HttpUtil {
	/**
	 * 渲染到客户端
	 * @param response
	 * @param object 待渲染的实体类，会自动转为json
	 * @throws IOException
	 */
	public static void render(HttpServletResponse response, Object object) throws IOException {
		// 允许跨域
		response.setHeader("Access-Control-Allow-Origin", "*");
		// 允许自定义请求头token(允许head跨域)
		response.setHeader("Access-Control-Allow-Headers", "token, Accept, Origin, X-Requested-With, Content-Type, Last-Modified");
		response.setHeader("Content-type", "application/json;charset=UTF-8");
		response.getWriter().print(JSON.toJSONString(object));
	}

	/**
	 * 获取request
	 */
	public static HttpServletRequest getRequest() {
		return getRequestAttributes().getRequest();
	}

	/**
	 * 获取response
	 */
	public static HttpServletResponse getResponse() {
		return getRequestAttributes().getResponse();
	}

	public static ServletRequestAttributes getRequestAttributes() {
		RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
		return (ServletRequestAttributes) attributes;
	}

	/**
	 * 获取请求头中的参数值
	 * @param headName 请求头名称
	 * @return 请求头值
	 */
	public static String getRequestHead(String headName) {
		HttpServletRequest request = getRequest();
		return request.getHeader(headName);
	}

	/**
	 * 获取请求token
	 * @param request 请求
	 * @return
	 */
	public static String getToken(HttpServletRequest request) {
		String token = request.getHeader(Constants.TOKEN_HEADER);
		if (StrUtil.isNotEmpty(token) && token.startsWith(Constants.TOKEN_PREFIX)) {
			token = token.replace(Constants.TOKEN_PREFIX, "");
		}
		return token;
	}
}

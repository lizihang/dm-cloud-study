package com.dm.study.cloud.handler;

import com.dm.study.cloud.util.RedisUtil;
import com.dm.study.cloud.util.HttpUtil;
import com.dm.study.cloud.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
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
 * <p>创建日期：2022年04月28日 11:28</p>
 * <p>类全名：com.dm.study.cloud.handler.LoginFailureHandler</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {
	private static final Logger logger = LoggerFactory.getLogger(LoginFailureHandler.class);
	@Resource
	RedisUtil redisUtil;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		Result result;
		// 为了解决缓存穿透问题，UsernameNotFoundException的时候不清redis缓存
		boolean flag = true;
		String username = request.getParameter("username");
		// AbstractUserDetailsAuthenticationProvider中，将UsernameNotFoundException改成了BadCredentialsException抛出
		if (exception instanceof UsernameNotFoundException) {
			// 用户名或密码错误
			logger.info("[登录失败] - 用户名或密码错误！");
			flag = false;
			result = Result.error(HttpStatus.FORBIDDEN.value(), "[登录失败] - 用户名或密码错误！");
		} else if (exception instanceof BadCredentialsException) {
			// 用户名或密码错误
			logger.info("[登录失败] - 用户名或密码错误！");
			result = Result.error(HttpStatus.FORBIDDEN.value(), "[登录失败] - 用户名或密码错误！");
		} else if (exception instanceof AccountExpiredException) {
			// 账号过期
			String msg = String.format("[登录失败] - 用户[%s]账号过期", username);
			logger.info(msg);
			result = Result.error(HttpStatus.FORBIDDEN.value(), msg);
		} else if (exception instanceof CredentialsExpiredException) {
			// 密码过期
			String msg = String.format("[登录失败] - 用户[%s]密码过期", username);
			logger.info(msg);
			result = Result.error(HttpStatus.FORBIDDEN.value(), msg);
		} else if (exception instanceof DisabledException) {
			// 用户被禁用
			String msg = String.format("[登录失败] - 用户[%s]被禁用", username);
			logger.info(msg);
			result = Result.error(HttpStatus.FORBIDDEN.value(), msg);
		} else if (exception instanceof LockedException) {
			// 用户被锁定
			String msg = String.format("[登录失败] - 用户[%s]被锁定", username);
			logger.info(msg);
			result = Result.error(HttpStatus.FORBIDDEN.value(), msg);
		} else if (exception instanceof InternalAuthenticationServiceException) {
			// 内部错误
			String msg = String.format("[登录失败] - [%s]内部错误", username);
			logger.error(msg, exception);
			result = Result.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg);
		} else {
			// 其他错误
			String msg = String.format("[登录失败] - [%s]其他错误", username);
			logger.error(msg, exception);
			result = Result.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg);
		}
		// 登录失败，清空redis缓存
		if (flag) {
			// TODO
			// redisUtil.deleteObject(Constants.USER_KEY + username);
		}
		HttpUtil.render(response, result);
	}
}

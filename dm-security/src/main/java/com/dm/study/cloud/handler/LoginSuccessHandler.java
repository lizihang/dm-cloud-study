package com.dm.study.cloud.handler;

import com.dm.study.cloud.constant.Constants;
import com.dm.study.cloud.util.RedisUtil;
import com.dm.study.cloud.util.HttpUtil;
import com.dm.study.cloud.vo.LoginUser;
import com.dm.study.cloud.vo.Result;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年05月04日 20:10</p>
 * <p>类全名：com.dm.study.cloud.handler.LoginSuccessHandler</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
	@Resource
	RedisUtil redisUtil;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		// TODO 登录成功 记录日志
		LoginUser loginUser = (LoginUser) authentication.getPrincipal();
		redisUtil.setCacheObject(Constants.USER_KEY + loginUser.getUsername(), loginUser.getToken(), 30, TimeUnit.MINUTES);
		Result result = Result.success("登录成功", loginUser);
		HttpUtil.render(response, result);
	}
}

package com.dm.study.cloud.handler;

import com.dm.study.cloud.constant.Constants;
import com.dm.study.cloud.util.JwtTokenUtil;
import com.dm.study.cloud.util.RedisUtil;
import com.dm.study.cloud.util.HttpUtil;
import com.dm.study.cloud.vo.Result;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
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
 * <p>创建日期：2022年05月04日 20:14</p>
 * <p>类全名：com.dm.study.cloud.handler.DmLogoutSuccessHandler</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Component
public class DmLogoutSuccessHandler implements LogoutSuccessHandler {
	@Resource
	RedisUtil    redisUtil;
	@Resource
	JwtTokenUtil jwtTokenUtil;

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
		// TODO 登出成功 记录登出日志
		// 1.登出成功，删除缓存
		String token = HttpUtil.getToken(request);
		String username = jwtTokenUtil.getUsernameFromToken(token);
		redisUtil.deleteCacheObject(Constants.USER_KEY + username);
		HttpUtil.render(response, Result.success("退出登录成功！"));
	}
}

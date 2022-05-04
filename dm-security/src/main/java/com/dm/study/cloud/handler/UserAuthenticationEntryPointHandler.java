package com.dm.study.cloud.handler;

import com.dm.study.cloud.util.ServletUtil;
import com.dm.study.cloud.vo.Result;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

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
 * <p>创建日期：2022年05月04日 20:11</p>
 * <p>类全名：com.dm.study.cloud.handler.UserAuthenticationEntryPointHandler</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Component
public class UserAuthenticationEntryPointHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Result result = Result.error(HttpStatus.UNAUTHORIZED.value(), "用户未登录！");
        ServletUtil.render(response, result);
    }
}

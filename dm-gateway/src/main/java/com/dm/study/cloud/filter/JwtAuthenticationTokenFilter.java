package com.dm.study.cloud.filter;

import com.dm.study.cloud.feign.ToUserFeign;
import com.dm.study.cloud.util.RedisUtil;
import com.dm.study.cloud.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * <p>标题：校验token</p>
 * <p>功能：自定义全局过滤器</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年05月02日 19:38</p>
 * <p>类全名：com.dm.study.cloud.filter.JwtAuthenticationTokenFilter</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Component
public class JwtAuthenticationTokenFilter implements GlobalFilter, Ordered {
    private final Logger logger = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);
    // @Resource
    // JwtTokenUtil jwtTokenUtil;
    @Resource
    RedisUtil redisUtil;
    @Resource
    ToUserFeign toUserFeign;

    ExecutorService executorService = Executors.newFixedThreadPool(1);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // WebFlux异步调用，同步会报错
        Future<Result> future = executorService.submit(() -> toUserFeign.testRedis("1"));
        Result result = null;
        try {
            result = future.get();
            logger.info(result.toString());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        logger.info("校验token全局filter");
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }

//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String url = request.getRequestURL().toString();
//        logger.info("请求地址url:" + url);
//        LoginUser loginUser = jwtTokenUtil.getLoginUser(request);
//        if (ObjectUtil.isNotEmpty(loginUser) && ObjectUtil.isEmpty(SecurityContextHolder.getContext().getAuthentication()))
//        {
//            // 如果token过期，清空redis中user缓存
//            if (jwtTokenUtil.isTokenExpired(request))
//            {
//                redisUtil.deleteObject(Constants.LOGIN_USER_KEY + loginUser.getUsername());
//                redisUtil.deleteObject(Constants.USER_KEY + loginUser.getUsername());
//            } else
//            {
//                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
//                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//            }
//        }
//        String token = jwtTokenUtil.getToken(request);
//        if (StrUtil.isNotEmpty(token) && ObjectUtil.isEmpty(loginUser))
//        {
//            // 当redis缓存中loginUser删除时，重新登录会查询user，此时如果redis中user存在，返回的是没有password的，会报登录失败
//            // 临时解决：缓存中loginUser不存在时，user缓存也清空。
//            // TODO loginUser和user缓存只留一个？
//            String username = jwtTokenUtil.getUsernameFromToken(request);
//            redisUtil.deleteObject(Constants.USER_KEY + username);
//        }
//        filterChain.doFilter(request, response);
//    }
}

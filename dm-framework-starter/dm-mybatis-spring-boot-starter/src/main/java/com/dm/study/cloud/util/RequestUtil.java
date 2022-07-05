package com.dm.study.cloud.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年07月05日 15:20</p>
 * <p>类全名：com.dm.study.cloud.util.RequestUtil</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class RequestUtil {
    /**
     * 获取request对象
     * @return
     */
    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
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
}

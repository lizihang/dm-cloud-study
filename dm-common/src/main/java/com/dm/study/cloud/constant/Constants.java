package com.dm.study.cloud.constant;

/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年03月16日 10:04</p>
 * <p>类全名：com.dm.study.cloud.constant.Constants</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public final class Constants {
    /***/
    public static final int      HTTP_STATUS_OK_VALUE  = 200;
    /** 用户 redis key */
    public static final String   USER_KEY              = "user:";
    /** 是否检查权限 */
    public static final boolean  IS_CHECK_PERMISSION   = false;
    /** 登录用户 redis key */
    public static final String   LOGIN_USER_KEY        = "login_user:";
    /** 请求中TOKEN名 */
    public static final String   TOKEN_HEADER          = "Authorization";
    /** 令牌前缀 注意后边带空格*/
    public static final String   TOKEN_PREFIX          = "Bearer ";
    /** 私钥 */
    public static final String   TOKEN_SECRET_KEY      = "dm_security";
    /** 过期时间 毫秒,设置默认1天的时间过期 */
    public static final long     TOKEN_EXPIRATION_TIME = 24 * 60 * 60 * 1000;
    // TODO 测试用
    // public static final long     TOKEN_EXPIRATION_TIME = 10 * 1000;
    /**==================== 错误码 ====================*/
    public static final String   ERR_CODE_00           = "当前登录用户不存在！请检查";
    /**==================== 不需要权限的 ====================*/
    public static final String[] AUTH_WHITELIST        = {
            // 登录相关
            "/login/getCodeImg", "/user/register",
            //
            "/oauth/**",
            // swagger3相关
            "/swagger-ui/**", "/v3/**", "/swagger-resources/**"
            // swagger2相关
            // "/swagger-ui.html", "/webjars/**", "/v2/**", "/swagger-resources/**"
            // 静态资源，头像
            , "/avatar/**"
            // 测试用
            , "/user/queryUserList", "/goods/**" };
}

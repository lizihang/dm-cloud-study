package com.dm.study.cloud.vo;

import com.dm.study.cloud.po.DmUser;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年05月04日 22:12</p>
 * <p>类全名：com.dm.study.cloud.vo.LoginUser</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class LoginUser implements UserDetails, CredentialsContainer {
    private static final long serialVersionUID = 766211942959789077L;

    /**
     * 用户信息
     */
    private DmUser user;
    /**
     * 用户唯一标识
     */
    private String token;
    /**
     * 登录时间
     */
    private Date loginTime;
    /**
     * 过期时间
     */
    private Long expireTime;
    /**
     * 登录IP地址
     */
    private String ip;
    /**
     * 登录地点
     */
    private String loginLocation;
    /**
     * 浏览器类型
     */
    private String browser;
    /**
     * 操作系统
     */
    private String os;
    /**
     * 权限列表
     */
    private Set<String> permissions;
    /**
     * 权限列表
     */
    private List<GrantedAuthority> authorities;

    public LoginUser() {
    }

    public LoginUser(DmUser user) {
        this.user = user;
    }

    public DmUser getUser() {
        return user;
    }

    public void setUser(DmUser user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getLoginLocation() {
        return loginLocation;
    }

    public void setLoginLocation(String loginLocation) {
        this.loginLocation = loginLocation;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        // 只判断是否禁用，其他判断为true
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // 只判断是否禁用，其他判断为true
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 只判断是否禁用，其他判断为true
        return true;
    }

    /**
     * 判断用户是否禁用
     *
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * 认证完成后，擦除密码
     */
    @Override
    public void eraseCredentials() {
        user.setPassword(null);
    }
}

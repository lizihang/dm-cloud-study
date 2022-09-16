package com.dm.study.cloud.service.impl;

import com.dm.study.cloud.po.SysUser;
import com.dm.study.cloud.vo.LoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年05月04日 20:02</p>
 * <p>类全名：com.dm.study.cloud.service.impl.UserDetailsServiceImpl</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Service()
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO
        SysUser user = new SysUser();
        user.setUsername("admin");
        user.setPassword(new BCryptPasswordEncoder().encode("123456"));
        if (user == null) {
            throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
        }
        return createLoginUser(user);
    }

    public UserDetails createLoginUser(SysUser user) {
        //TODO 转换对象
        logger.info("[用户登录]-将DmUser转换成LoginUser");
        LoginUser loginUser = new LoginUser(user);
        // 1.生成token
        //loginUser.setToken(jwtTokenUtil.generateToken(loginUser));
        // 2.处理权限
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("/user/queryList");
        grantedAuthorities.add(grantedAuthority);
        loginUser.setAuthorities(grantedAuthorities);
        // 3.处理其他信息
        return loginUser;
    }
}

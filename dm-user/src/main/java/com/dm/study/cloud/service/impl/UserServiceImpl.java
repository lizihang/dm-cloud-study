package com.dm.study.cloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dm.study.cloud.dao.UserMapper;
import com.dm.study.cloud.param.DmUserQueryParams;
import com.dm.study.cloud.po.DmUser;
import com.dm.study.cloud.service.UserService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年04月18日 21:20</p>
 * <p>类全名：com.dm.study.cloud.service.impl.UserServiceImpl</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,DmUser> implements UserService {

    private String beanName;
    @Resource
    UserMapper userMapper;

    @Override
    public Page<DmUser> queryPage(DmUserQueryParams params) {
        Page<DmUser> page = new Page<>(params.getPageNum(), params.getPageSize());
        QueryWrapper<DmUser> wrapper = buildQueryWrapper(params);
        Page<DmUser> dmUserPage = userMapper.selectPage(page, wrapper);
        dmUserPage.getRecords().forEach(user -> user.setPassword(null));
        return dmUserPage;
    }

    @Override
    public DmUser queryUser(DmUserQueryParams params) {
        QueryWrapper<DmUser> wrapper = buildQueryWrapper(params);
        return userMapper.selectOne(wrapper);
    }

    @Override
    public void setBeanName(@NonNull String name) {
        this.beanName = name;
    }

    @Override
    @NonNull
    public String getBeanName() {
        return beanName;
    }

    /**
     * 处理查询wrapper
     *
     * @param params
     * @return
     */
    private QueryWrapper<DmUser> buildQueryWrapper(DmUserQueryParams params) {
        QueryWrapper<DmUser> wrapper = new QueryWrapper<>();
        if (params.getId() != null) {
            wrapper.eq("id", params.getId());
        }
        if (params.getUsername() != null) {
            wrapper.like("username", params.getUsername());
        }
        if (params.getNickname() != null) {
            wrapper.like("nickname", params.getNickname());
        }
        if (params.getEmail() != null) {
            wrapper.like("email", params.getEmail());
        }
        if (params.getStatus() != null) {
            wrapper.eq("status", params.getStatus());
        }
        return wrapper;
    }
}

package com.dm.study.cloud.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dm.study.cloud.param.DmUserQueryParams;
import com.dm.study.cloud.po.DmUser;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.NamedBean;

/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年04月18日 21:06</p>
 * <p>类全名：com.dm.study.cloud.service.UserService</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public interface UserService extends BeanNameAware, NamedBean {
    /**
     * 查询用户分页数据
     * @return 用户列表
     */
    Page<DmUser> queryPage(DmUserQueryParams params);

    /**
     * 查询用户信息
     * @return 用户对象
     */
    DmUser queryUser(DmUserQueryParams params);
}

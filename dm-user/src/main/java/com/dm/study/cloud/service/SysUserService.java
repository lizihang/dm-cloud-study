package com.dm.study.cloud.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dm.study.cloud.param.DmUserQueryParams;
import com.dm.study.cloud.po.SysUser;

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
 * <p>创建日期：2022年09月16日 11:05</p>
 * <p>类全名：com.dm.study.cloud.service.SysUserService</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public interface SysUserService extends IService<SysUser> {
	/**
	 * 查询用户分页数据
	 * @param params 参数
	 * @return 用户分页数据
	 */
	Page<SysUser> queryUserPage(DmUserQueryParams params);

	/**
	 * 查询用户列表
	 * @param params 参数
	 * @return 用户列表
	 */
	List<SysUser> queryUserList(DmUserQueryParams params);

	/**
	 * 查询用户信息
	 * @param params 参数
	 * @return 用户对象
	 */
	SysUser queryUserInfo(DmUserQueryParams params);
}

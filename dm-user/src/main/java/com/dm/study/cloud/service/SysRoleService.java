package com.dm.study.cloud.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dm.study.cloud.param.SysRoleQueryParams;
import com.dm.study.cloud.po.SysRole;
import com.dm.study.cloud.vo.IdParam;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年09月16日 11:08</p>
 * <p>类全名：com.dm.study.cloud.service.SysRoleService</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public interface SysRoleService extends IService<SysRole> {
	/**
	 * 查询角色分页数据
	 * @param params
	 * @return
	 */
	Page<SysRole> queryRolePage(SysRoleQueryParams params);

	SysRole addRole(SysRole role);

	SysRole updateRole(SysRole role);

	void deleteRole(IdParam param);
}

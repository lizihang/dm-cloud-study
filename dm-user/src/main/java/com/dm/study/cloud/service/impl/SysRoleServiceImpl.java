package com.dm.study.cloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dm.study.cloud.dao.SysRoleMapper;
import com.dm.study.cloud.param.SysRoleQueryParams;
import com.dm.study.cloud.po.SysRole;
import com.dm.study.cloud.service.SysRoleService;
import com.dm.study.cloud.vo.IdParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年09月16日 11:10</p>
 * <p>类全名：com.dm.study.cloud.service.impl.SysRoleServiceImpl</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper,SysRole> implements SysRoleService {
	@Override
	public Page<SysRole> queryRolePage(SysRoleQueryParams params) {
		Page<SysRole> page = new Page<>(params.getPageNum(), params.getPageSize());
		LambdaQueryWrapper<SysRole> wrapper = buildQueryWrapper(params);
		return baseMapper.selectPage(page, wrapper);
	}

	@Override
	public SysRole addRole(SysRole role) {
		baseMapper.insert(role);
		return role;
	}

	@Override
	public SysRole updateRole(SysRole role) {
		baseMapper.updateById(role);
		return role;
	}

	@Override
	public void deleteRole(IdParam param) {
		baseMapper.deleteById(param.getId());
	}

	/**
	 * 构建查询wrapper
	 * @param params 参数
	 * @return wrapper
	 */
	private LambdaQueryWrapper<SysRole> buildQueryWrapper(SysRoleQueryParams params) {
		LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<>();
		wrapper.like(StringUtils.isNotEmpty(params.getRoleName()), SysRole::getRoleName, params.getRoleName());
		return wrapper;
	}
}

package com.dm.study.cloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dm.study.cloud.dao.SysUserMapper;
import com.dm.study.cloud.param.DmUserQueryParams;
import com.dm.study.cloud.po.SysUser;
import com.dm.study.cloud.service.SysUserService;
import org.springframework.stereotype.Service;

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
 * <p>创建日期：2022年09月16日 11:06</p>
 * <p>类全名：com.dm.study.cloud.service.impl.SysUserServiceImpl</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper,SysUser> implements SysUserService {
	@Override
	public Page<SysUser> queryUserPage(DmUserQueryParams params) {
		Page<SysUser> page = new Page<>(params.getPageNum(), params.getPageSize());
		QueryWrapper<SysUser> wrapper = buildQueryWrapper(params);
		Page<SysUser> dmUserPage = baseMapper.selectPage(page, wrapper);
		dmUserPage.getRecords().forEach(user -> user.setPassword(null));
		return dmUserPage;
	}

	@Override
	public List<SysUser> queryUserList(DmUserQueryParams params) {
		QueryWrapper<SysUser> wrapper = buildQueryWrapper(params);
		return baseMapper.selectList(wrapper);
	}

	@Override
	public SysUser queryUserInfo(DmUserQueryParams params) {
		QueryWrapper<SysUser> wrapper = buildQueryWrapper(params);
		return baseMapper.selectOne(wrapper);
	}

	/**
	 * 构建查询wrapper
	 * @param params
	 * @return
	 */
	private QueryWrapper<SysUser> buildQueryWrapper(DmUserQueryParams params) {
		QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
		wrapper.eq(params.getId() != null, "id", params.getId());
		wrapper.like(params.getUsername() != null, "username", params.getUsername());
		wrapper.like(params.getNickname() != null, "nickname", params.getNickname());
		wrapper.like(params.getEmail() != null, "email", params.getEmail());
		wrapper.eq(params.getStatus() != null, "status", params.getStatus());
		return wrapper;
	}
}

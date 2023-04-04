package com.dm.study.cloud.controller;

import com.dm.study.cloud.annotation.DmLog;
import com.dm.study.cloud.param.SysRoleQueryParams;
import com.dm.study.cloud.po.SysRole;
import com.dm.study.cloud.service.SysRoleService;
import com.dm.study.cloud.vo.IdParam;
import com.dm.study.cloud.vo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
 * <p>创建日期：2022年08月15日 14:54</p>
 * <p>类全名：com.dm.study.cloud.controller.SysRoleController</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@RestController
@RequestMapping("/role")
public class SysRoleController {
	@Resource
	SysRoleService service;

	/**
	 * 查询所有角色列表
	 * @param params 查询参数
	 * @return result
	 */
	@DmLog
	@PostMapping("/queryRoleList")
	public Result queryList(@RequestBody SysRoleQueryParams params) {
		return Result.success("查询成功！", service.queryRolePage(params));
	}

	/**
	 * 新增角色
	 * @param role 角色
	 * @return 结果
	 */
	@DmLog
	@PostMapping("/addRole")
	public Result addRole(@RequestBody SysRole role) {
		return Result.success("新增成功！", service.addRole(role));
	}

	/**
	 * 编辑角色
	 * @param role 角色
	 * @return 结果
	 */
	@DmLog
	@PostMapping("/updateRole")
	public Result updateRole(@RequestBody SysRole role) {
		return Result.success("编辑成功！", service.updateRole(role));
	}

	/**
	 * 删除角色
	 * @param param 角色ID
	 * @return 结果
	 */
	@DmLog
	@PostMapping("/deleteRole")
	public Result deleteRole(@RequestBody IdParam param) {
		service.deleteRole(param);
		return Result.success("删除成功！");
	}
}

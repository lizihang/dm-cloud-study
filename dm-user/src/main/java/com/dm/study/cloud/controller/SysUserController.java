package com.dm.study.cloud.controller;

import com.dm.study.cloud.param.DmUserQueryParams;
import com.dm.study.cloud.service.SysUserService;
import com.dm.study.cloud.vo.Result;
import org.springframework.web.bind.annotation.PostMapping;
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
 * <p>创建日期：2022年09月16日 11:04</p>
 * <p>类全名：com.dm.study.cloud.controller.SysUserController</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@RestController
@RequestMapping("/user")
public class SysUserController {
	@Resource
	SysUserService userService;

	/**
	 * 查询用户分页信息
	 * @param params 参数
	 * @return 用户分页数据
	 */
	@PostMapping("/queryUserPage")
	public Result queryUserPage(DmUserQueryParams params) {
		return Result.success("查询成功！", userService.queryUserPage(params));
	}

	/**
	 * 查询用户列表信息
	 * @param params 参数
	 * @return 用户列表数据
	 */
	@PostMapping("/queryUserList")
	public Result queryUserList(DmUserQueryParams params) {
		return Result.success("查询成功！", userService.queryUserList(params));
	}

	/**
	 * 查询用户信息
	 * @param params 参数
	 * @return 用户信息
	 */
	@PostMapping("/queryUserInfo")
	public Result queryUserInfo(DmUserQueryParams params) {
		return Result.success("查询成功！", userService.queryUserInfo(params));
	}
}

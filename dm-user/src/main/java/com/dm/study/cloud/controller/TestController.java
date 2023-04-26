package com.dm.study.cloud.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dm.study.cloud.config.MyNacosProperties;
import com.dm.study.cloud.exception.DmException;
import com.dm.study.cloud.feign.GoodsFeignService;
import com.dm.study.cloud.param.DmUserQueryParams;
import com.dm.study.cloud.po.SysUser;
import com.dm.study.cloud.po.TestGroupingBy;
import com.dm.study.cloud.service.SysUserService;
import com.dm.study.cloud.vo.Result;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
/**
 * <p>标题：测试用controller</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年03月15日 15:52</p>
 * <p>类全名：com.dm.study.cloud.controller.UserController</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@RestController
@RequestMapping("/test")
public class TestController {
	@Resource
	SysUserService    userService;
	@Resource
	RedissonClient    client;
	@Resource
	GoodsFeignService goodsFeignService;
	@Resource
	MyNacosProperties myNacosProperties;
	@Value("${server.port}")
	int               port;

	@GetMapping("/testFeign/queryGoods/{id}")
	public Result queryGoods(@PathVariable String id) {
		List<SysUser> list = userService.list(new LambdaQueryWrapper<SysUser>().eq(SysUser::getId, id));
		Result result = goodsFeignService.getGoods(id);
		if (result.getStatus() == 200) {
			Object data = result.getData();
			System.out.println(data.toString());
		}
		return Result.success("查询成功！", list);
	}

	@GetMapping("/testMyBatisInterceptor/{id}")
	public Result testMyBatisInterceptor(@PathVariable String id) {
		SysUser user = new SysUser();
		user.setUsername("测试拦截器");
		user.setNickname("test" + id);
		user.setPassword("12345");
		user.setStatus(1);
		user.setEmail("testUser@163.com");
		user.setGender(1);
		userService.save(user);
		return Result.success("保存成功！");
	}

	@GetMapping("/testGetInterceptor/{id}")
	public Result testGetInterceptor(@PathVariable String id) {
		QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
		List<String> emails = new ArrayList<>();
		emails.add("test@163.com");
		emails.add("testUser@163.com");
		wrapper.in("email", emails);
		List<SysUser> list = userService.list(wrapper);
		SysUser byId = userService.getById(id);
		return Result.success("查询成功！", list);
	}

	@GetMapping("/testGetInterceptor2/{id}")
	public Result testGetInterceptor2(@PathVariable String id) {
		DmUserQueryParams params = new DmUserQueryParams();
		params.setEmail("testUser@163.com");
		List<SysUser> list = userService.queryUserList(params);
		return Result.success("查询成功！", list);
	}

	@GetMapping("/testGetInterceptor3/{id}")
	public Result testGetInterceptor3(@PathVariable String id) {
		QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
		wrapper.in("email", "testUser@163.com", "test@163.com");
		wrapper.like("username", "测试");
		List<SysUser> list = userService.list(wrapper);
		return Result.success("查询成功！", list);
	}

	@GetMapping("/testGetInterceptor4/{id}")
	public Result testGetInterceptor4(@PathVariable String id) {
		LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
		wrapper.in(SysUser::getEmail, "testUser@163.com", "test@163.com");
		wrapper.like(SysUser::getUsername, "测试");
		List<SysUser> list = userService.list(wrapper);
		return Result.success("查询成功！", list);
	}

	@PostMapping("/testPostMethod")
	public Result testPostMethod(@RequestBody DmUserQueryParams param) {
		return Result.success(param.toString());
	}

	@PostMapping("/testPostMethod2")
	public Result testPostMethod2(DmUserQueryParams param) {
		return Result.success(param.toString());
	}

	@GetMapping("/testGetNacosProperties")
	public Result testGetNacosProperties() {
		return Result.success("查询成功！", myNacosProperties.isUseLocalCache());
	}

	@GetMapping("/testGroupingBy")
	public Result testGroupingBy() {
		List<SysUser> userList = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			SysUser user = new SysUser();
			user.setUsername("username" + i);
			user.setStatus(i % 3);
			userList.add(user);
		}
		Map<Integer,List<SysUser>> collect = userList.stream().collect(Collectors.groupingBy(SysUser::getStatus));
		TestGroupingBy test = new TestGroupingBy();
		test.setUserList1(collect.get(0));
		test.setUserList2(collect.get(1));
		test.setUserList3(collect.get(2));
		test.setAllList(userList);
		return Result.success("ok", test);
	}

	/**
	 * 测试全局异常处理
	 * @return
	 */
	@GetMapping("/testGlobalExceptionHandler")
	public Result testGlobalExceptionHandler() {
		throw new DmException("测试全局异常处理");
	}

	/**
	 * 测试全自定义负载均衡
	 * @return
	 */
	@PostMapping("/queryPort")
	public Result queryPort() {
		return Result.success("本服务端口为：" + port);
	}
}

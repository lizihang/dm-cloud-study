package com.dm.study.cloud.feign;

import com.dm.study.cloud.po.SysUser;
import com.dm.study.cloud.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
/**
 * <p>标题：公共接口方式实现feign</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * 1.服务提供者实现本接口，参考 UserFeignController
 * 2.服务消费者继承本接口，参考 GatewayToUserService
 * 3.当前版本，接口上不能有@RequestMapping注解，请求路径全部写在方法的注解上。
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年09月16日 10:21</p>
 * <p>类全名：com.dm.study.cloud.feign.ToUserFeign</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public interface ToUserService {
	@GetMapping("/user/testFeign")
	Result testFeign();

	@PostMapping("/queryUserByUsername")
	SysUser queryUserByUsername(String username);
}

package com.dm.study.cloud.controller.feign;

import com.dm.study.cloud.feign.ToUserService;
import com.dm.study.cloud.vo.Result;
import org.springframework.web.bind.annotation.RestController;
/**
 * <p>标题：feign调用controller</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：实现公共接口，实现接口方法，需要增加@RestController注解。
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年09月16日 10:37</p>
 * <p>类全名：com.dm.study.cloud.controller.feign.UserFeignController</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@RestController
public class UserFeignController implements ToUserService {
	/**
	 * 测试公共接口实现feign调用
	 * @return
	 */
	@Override
	public Result testFeign() {
		return Result.success("feign调用成功！");
	}
}

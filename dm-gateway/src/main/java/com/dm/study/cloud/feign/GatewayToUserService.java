package com.dm.study.cloud.feign;

import com.dm.study.cloud.constant.ServiceNameConstant;
import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>标题：feign client</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：继承公共接口，不需要写方法，只需要增加@FeignClient注解，可以自定义fallbackFactory处理异常
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年09月16日 10:25</p>
 * <p>类全名：com.dm.study.cloud.feign.GatewayToUserService</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@FeignClient(name = ServiceNameConstant.DM_USER)
public interface GatewayToUserService extends ToUserService {
}

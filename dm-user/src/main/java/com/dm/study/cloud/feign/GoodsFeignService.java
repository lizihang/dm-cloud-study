package com.dm.study.cloud.feign;

import com.dm.study.cloud.constant.ServiceNameConstant;
import com.dm.study.cloud.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年03月16日 10:00</p>
 * <p>类全名：com.dm.study.cloud.feign.GoodsFeignService</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@FeignClient(name = ServiceNameConstant.DM_GOODS)
public interface GoodsFeignService {
	@GetMapping("/feign/goods/getGoods/{id}")
	Result getGoods(@PathVariable("id") String id);
}

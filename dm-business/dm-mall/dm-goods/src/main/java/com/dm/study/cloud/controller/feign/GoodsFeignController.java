package com.dm.study.cloud.controller.feign;

import com.dm.study.cloud.po.Good;
import com.dm.study.cloud.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年11月22日 14:12</p>
 * <p>类全名：com.dm.study.cloud.controller.feign.GoodsFeignController</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@RestController
@RequestMapping("/feign/goods")
public class GoodsFeignController {
	@GetMapping("/getGoods/{id}")
	public Result getGoods(@PathVariable String id) {
		Good good = new Good();
		good.setGoodCode("good-" + id);
		good.setGoodName("商品-" + id);
		return Result.success("查询成功", good);
	}
}

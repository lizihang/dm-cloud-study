package com.dm.study.cloud.controller;

import com.dm.study.cloud.feign.GoodsFeignService;
import com.dm.study.cloud.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * <p>标题：</p>
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
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    GoodsFeignService goodsFeignService;

    @GetMapping("/getUser/{id}")
    public Result getUser(@PathVariable String id) {
        Result goodsResult = restTemplate.getForObject("http://dm-goods/goods/getGoods/" + id, Result.class);
        assert goodsResult != null;
        return Result.success("user id:" + id + ";" + goodsResult.getMsg());
    }

    @GetMapping("/getUserByFeign/{id}")
    public Result getUser2(@PathVariable String id) {
        logger.info("进入到getUser方法");
        Result goodsResult = goodsFeignService.getGoods(id);
        assert goodsResult != null;
        return Result.success("user id:" + id + ";" + goodsResult.getMsg());
    }
}

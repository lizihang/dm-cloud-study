package com.dm.study.cloud.controller;

import com.dm.study.cloud.feign.GoodsFeignService;
import com.dm.study.cloud.param.DmUserQueryParams;
import com.dm.study.cloud.po.DmUser;
import com.dm.study.cloud.service.UserService;
import com.dm.study.cloud.vo.Result;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
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
    UserService userService;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    GoodsFeignService goodsFeignService;
    @Autowired
    RedissonClient client;

    @GetMapping("/testRedis/{id}")
    public Result testRedis(@PathVariable String id) {
        RBucket<String> bucket = client.getBucket("test");
        bucket.set("value-" + id);
        String value = bucket.get();
        DmUserQueryParams params = new DmUserQueryParams();
        params.setId(Integer.parseInt(id));
        DmUser dmUser = userService.queryUser(params);
        return Result.success("test redis value:" + value + ";DmUser:" + dmUser.toString());
    }

    @GetMapping("/getUser/{id}")
    public Result getUser(@PathVariable String id) {
        logger.info("进入到getUser方法");
        Result goodsResult = restTemplate.getForObject("http://dm-goods/goods/getGoods/" + id, Result.class);
        assert goodsResult != null;
        return Result.success("user id:" + id + ";" + goodsResult.getMsg());
    }

    @GetMapping("/getUserByFeign/{id}")
    public Result getUserByFeign(@PathVariable String id) {
        logger.info("进入到getUserByFeign方法");
        Result goodsResult = goodsFeignService.getGoods(id);
        assert goodsResult != null;
        return Result.success("user id:" + id + ";" + goodsResult.getMsg());
    }
}

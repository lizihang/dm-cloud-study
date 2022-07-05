package com.dm.study.cloud.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.dm.study.cloud.util.RequestUtil;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * <p>标题：mybatis自动插入公共字段</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年05月03日 16:48</p>
 * <p>类全名：com.dm.study.cloud.handler.MyMetaObjectHandler</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    private static final Logger logger = LoggerFactory.getLogger(MyMetaObjectHandler.class);

    @Override
    public boolean openInsertFill() {
        // 可通过系统配置动态控制
        return true;
    }

    @Override
    public boolean openUpdateFill() {
        // 可通过系统配置动态控制
        return true;
    }

    @Override
    public void insertFill(MetaObject metaObject) {
        // 对象中不存在的字段，不会处理，也不会报错
        logger.info("start insert fill ....");
        // TODO 网关校验token后，将token对应的userId放到请求头参数中。此处获取
        String userId = RequestUtil.getRequestHead("user-id");
        Date date = new Date();
        this.strictInsertFill(metaObject, "createUser", String.class, userId);
        this.strictInsertFill(metaObject, "createTime", Date.class, date);
        this.strictInsertFill(metaObject, "modifyUser", String.class, userId);
        this.strictInsertFill(metaObject, "modifyTime", Date.class, date);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        logger.info("start update fill ....");
        // TODO 网关校验token后，将token对应的userId放到请求头参数中。此处获取
        String userId = RequestUtil.getRequestHead("user-id");
        Date date = new Date();
        this.strictUpdateFill(metaObject, "modifyUser", String.class, userId);
        this.strictUpdateFill(metaObject, "modifyTime", Date.class, date);
    }
}

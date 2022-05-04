package com.dm.study.cloud.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * <p>标题：</p>
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
    public void insertFill(MetaObject metaObject) {
        // 对象中不存在的字段，不会处理，也不会报错
        logger.info("start insert fill ....");
        Date date = new Date();
        // TODO 获取当前登录人
        // String usernameFromToken = jwtTokenUtil.getUsernameFromToken(ServletUtil.getRequest());
        String usernameFromToken = "";
        this.strictInsertFill(metaObject, "createUser", String.class, usernameFromToken);
        this.strictInsertFill(metaObject, "createTime", Date.class, date);
        this.strictInsertFill(metaObject, "modifyUser", String.class, usernameFromToken);
        this.strictInsertFill(metaObject, "modifyTime", Date.class, date);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        logger.info("start update fill ....");
        Date date = new Date();
        // TODO 获取当前登录人
        // String usernameFromToken = jwtTokenUtil.getUsernameFromToken(ServletUtil.getRequest());
        String usernameFromToken = "";
        this.strictUpdateFill(metaObject, "modifyUser", String.class, usernameFromToken);
        this.strictUpdateFill(metaObject, "modifyTime", Date.class, date);
    }
}

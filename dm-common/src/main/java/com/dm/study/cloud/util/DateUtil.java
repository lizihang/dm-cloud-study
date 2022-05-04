package com.dm.study.cloud.util;

import org.apache.commons.lang3.time.DateUtils;

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
 * <p>创建日期：2022年05月03日 16:54</p>
 * <p>类全名：com.dm.study.cloud.util.DateUtil</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class DateUtil extends DateUtils {
    /**
     * 获取当前日期
     * @return
     */
    public static Date getServerDate()
    {
        return new Date();
    }
}

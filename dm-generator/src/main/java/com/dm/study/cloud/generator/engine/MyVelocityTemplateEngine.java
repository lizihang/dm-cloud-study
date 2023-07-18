package com.dm.study.cloud.generator.engine;

import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2021年07月05日 22:04</p>
 * <p>类全名：com.dm.study.cloud.generator.engine.MyVelocityTemplateEngine</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class MyVelocityTemplateEngine extends VelocityTemplateEngine {
	@Override
	public Map<String,Object> getObjectMap(TableInfo tableInfo) {
		Map<String,Object> objectMap = super.getObjectMap(tableInfo);
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		objectMap.put("YEAR", c.get(Calendar.YEAR));
		objectMap.put("MONTH", c.get(Calendar.MONTH) + 1);
		objectMap.put("DAY", c.get(Calendar.DATE));
		objectMap.put("TIME", c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE));
		return objectMap;
	}
}

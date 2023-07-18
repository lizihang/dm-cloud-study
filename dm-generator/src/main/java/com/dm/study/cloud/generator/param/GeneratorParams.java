package com.dm.study.cloud.generator.param;

import java.io.Serializable;
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
 * <p>创建日期：2023年07月17日 15:19</p>
 * <p>类全名：com.dm.study.cloud.generator.param.GeneratorParams</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class GeneratorParams implements Serializable {
	private static final long               serialVersionUID = 6428995855667352796L;
	// 模块路径
	private              String             modulePath;
	// 模块名称
	private              String             moduleName;
	// 包路径
	private              String             packagePath;
	// 实体对象表名Map
	private              Map<String,String> entityTableMap;

	public String getModulePath() {
		return modulePath;
	}

	public void setModulePath(String modulePath) {
		this.modulePath = modulePath;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getPackagePath() {
		return packagePath;
	}

	public void setPackagePath(String packagePath) {
		this.packagePath = packagePath;
	}

	public Map<String,String> getEntityTableMap() {
		return entityTableMap;
	}

	public void setEntityTableMap(Map<String,String> entityTableMap) {
		this.entityTableMap = entityTableMap;
	}
}

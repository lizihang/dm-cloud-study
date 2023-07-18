package com.dm.study.cloud.generator;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.dm.study.cloud.generator.engine.MyVelocityTemplateEngine;
import com.dm.study.cloud.generator.param.GeneratorParams;
import com.dm.study.cloud.po.BasePO;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.*;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * 优点：
 * 		1.根据表生成各个层的类，节省时间
 * 		2.可以根据模板生成，例如模板:/templates/entity.java.vm
 * 		3.可以将代码生成到不同的模块下
 * 缺点：
 * 		1.只能生成单表
 * 		2.PO对象如何排除BasePO的字段？
 * 		3.controller层RequestMapping
 * 		4.此版本只支持swagger2
 * 已优化：
 * 		1.根据输入的模块路径，将类分别生成到其目录下（xxx-api,xxx-service,xxx-ui）
 * 		2.xxx-impl模块service注解@DubboService
 * 		3.修改文件生成模板，达到定制目的
 * 		4.mapper.xml输出路径修改为xxx-impl模块resource/com/dm/mapper目录下
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2021年07月05日 19:26</p>
 * <p>类全名：com.dm.study.cloud.generator.CodeGenerator</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class CodeGenerator {
	private static final String[] modules = new String[] { "api", "impl", "ui" };

	public static void main(String[] args) {
		setGeneratorConfig();
	}

	public static void setGeneratorConfig() {
		Scanner scanner = new Scanner(System.in);
		GeneratorParams params = new GeneratorParams();
		System.out.println("请输入微服务名称：");
		String moduleName = scanner.nextLine();
		params.setModuleName(moduleName);
		System.out.println("请输入微服务绝对路径：");
		String modulePath = scanner.nextLine();
		params.setModulePath(modulePath);
		System.out.println("请输入包路径：");
		String packagePath = scanner.nextLine();
		params.setPackagePath(packagePath);
		System.out.println("请输入实体对象名称（多个用英文逗号分隔）：");
		String entityNames = scanner.nextLine();
		String[] entityNameArr = entityNames.split(",");
		Map<String,String> entityTableMap = new HashMap<>();
		for (String entityName : entityNameArr) {
			if (StringUtils.isNotEmpty(entityName)) {
				System.out.println("请输入对象<" + entityName + ">的表名：");
				String tableName = scanner.nextLine();
				entityTableMap.put(entityName, tableName);
			}
		}
		params.setEntityTableMap(entityTableMap);
		System.out.println("开始生成代码");
		singleGeneratorExecute(params);
		System.out.println("代码生成完毕");
	}

	private static void singleGeneratorExecute(GeneratorParams params) {
		// 代码生成器
		AutoGenerator mpg = new AutoGenerator();
		// 全局配置
		mpg.setGlobalConfig(getGlobalConfig(params.getModulePath() + File.separator + params.getModuleName()));
		// 数据源配置
		mpg.setDataSource(getDataSourceConfig());
		// 包配置
		mpg.setPackageInfo(getPackageConfig(params.getPackagePath()));
		// 模板配置
		mpg.setTemplate(getTemplateConfig());
		// 策略配置
		mpg.setStrategy(getStrategyConfig("dm_goods,dm_book"));
		// 设置engine
		mpg.setTemplateEngine(new MyVelocityTemplateEngine());
		// 配置mapper.xml输出路径
		mpg.setCfg(getInjectionConfig(params.getModulePath() + File.separator + params.getModuleName()));
		// 执行
		mpg.execute();
	}

	private static GlobalConfig getGlobalConfig(String modulePath) {
		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		gc.setOutputDir(modulePath + "/src/main/java");
		gc.setAuthor("lizh");
		gc.setOpen(false);
		// Date类型用java.util的
		gc.setDateType(DateType.ONLY_DATE);
		// 不覆盖已有文件
		gc.setFileOverride(false);
		gc.setServiceName("%sService");
		gc.setServiceImplName("%sServiceImpl");
		// 配置mapper名称
		gc.setMapperName("%sMapper");
		return gc;
	}

	private static DataSourceConfig getDataSourceConfig() {
		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setUrl("jdbc:mysql://127.0.0.1:3306/dm?serverTimezone=UTC&characterEncoding=UTF-8&useSSL=false");
		dsc.setDriverName("com.mysql.jdbc.Driver");
		dsc.setUsername("root");
		dsc.setPassword("root");
		return dsc;
	}

	private static PackageConfig getPackageConfig(String packagePath) {
		// 包配置
		PackageConfig pc = new PackageConfig();
		pc.setParent(packagePath);
		pc.setModuleName("test");
		pc.setController("controller");
		pc.setService("service");
		pc.setServiceImpl("service.impl");
		pc.setMapper("mapper");
		pc.setXml(null);
		pc.setEntity("model");
		return pc;
	}

	private static TemplateConfig getTemplateConfig() {
		// 模板配置
		TemplateConfig tc = new TemplateConfig();
		tc.setController("/templates/controller.java");
		tc.setService("/templates/service.java");
		tc.setServiceImpl("/templates/serviceImpl.java");
		tc.setMapper("/templates/mapper.java");
		tc.setEntity("/templates/entity.java");
		return tc;
	}

	private static StrategyConfig getStrategyConfig(String tableNames) {
		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		// 配置表，可以多个
		strategy.setInclude(tableNames.split(","));
		strategy.setNaming(NamingStrategy.underline_to_camel);
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);
		strategy.setSuperEntityClass(BasePO.class);
		// TODO 作用？
		strategy.setSuperEntityColumns("createUser", "createTime", "modifyUser", "modifyTime");
		strategy.setEntityLombokModel(true);
		strategy.setRestControllerStyle(true);
		// 自动生成的是1L，设置为false，生成类以后手动生成序列化ID
		strategy.setEntitySerialVersionUID(false);
		return strategy;
	}

	private static InjectionConfig getInjectionConfig(String modulePath) {
		InjectionConfig cfg = new InjectionConfig() {
			@Override
			public void initMap() {
				// to do nothing
			}
		};
		String templatePath = "/templates/mapper.xml.vm";
		// 自定义输出配置
		List<FileOutConfig> focList = new ArrayList<>();
		// 自定义配置会被优先输出
		focList.add(new FileOutConfig(templatePath) {
			@Override
			public String outputFile(TableInfo tableInfo) {
				// 自定义输出路径
				return modulePath + "/src/main/resources/com/dm/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
			}
		});
		cfg.setFileOutConfigList(focList);
		return cfg;
	}

	/**
	 * <p>
	 * 读取控制台内容
	 * </p>
	 */
	public static String scanner(String tip) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入" + tip + "：");
		if (scanner.hasNext()) {
			String ipt = scanner.next();
			if (StringUtils.isNotBlank(ipt)) {
				return ipt;
			}
		}
		throw new MybatisPlusException("请输入正确的" + tip + "！");
	}

	/**
	 * <p>
	 * 读取控制台内容
	 * </p>
	 */
	public static String scanner2() {
		Scanner scanner = new Scanner(System.in);
		if (scanner.hasNext()) {
			String ipt = scanner.next();
			if (StringUtils.isNotBlank(ipt)) {
				return ipt;
			}
		}
		throw new MybatisPlusException("请输入正确的参数！");
	}
}

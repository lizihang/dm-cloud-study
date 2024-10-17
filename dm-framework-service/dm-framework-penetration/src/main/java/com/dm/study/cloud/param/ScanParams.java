package com.dm.study.cloud.param;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2024年01月31日 16:05</p>
 * <p>类全名：com.dm.study.cloud.param.ScanParams</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Getter
@Setter
public class ScanParams implements Serializable {
	private static final long    serialVersionUID = -2136838672701908963L;
	// ip地址
	private              String  ipAddress;
	// 端口类型
	private              String  portType;
	// 端口列表
	private              String  portList;
	// 线程数
	private              Integer threadNum;
	// 开启Ping扫描
	private              Boolean pingScan;
	// 密码破解
	private              Boolean pwdCracking;
	// POC检测
	private              Boolean poc;
	// 使用代理
	private              Boolean useProxy;
	// 结果文件名
	private              String  outName;
}

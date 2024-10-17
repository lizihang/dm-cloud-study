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
 * <p>创建日期：2024年09月11日 14:46</p>
 * <p>类全名：com.dm.study.cloud.param.ChainParam</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Getter
@Setter
public class ChainParam implements Serializable {
	private static final long   serialVersionUID = -7036493150671584778L;
	private              String chainId;
}

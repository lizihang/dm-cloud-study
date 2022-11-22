package com.dm.study.cloud.po;

/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年11月22日 14:14</p>
 * <p>类全名：com.dm.study.cloud.po.Good</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class Good extends BasePO {
	private static final long   serialVersionUID = 5537454673797730900L;
	/**
	 * 商品编号
	 */
	private              String goodCode;
	/**
	 * 商品名称
	 */
	private              String goodName;

	public String getGoodCode() {
		return goodCode;
	}

	public void setGoodCode(String goodCode) {
		this.goodCode = goodCode;
	}

	public String getGoodName() {
		return goodName;
	}

	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}

	@Override
	public String toString() {
		return "Good{" + "goodCode='" + goodCode + '\'' + ", goodName='" + goodName + '\'' + "} " + super.toString();
	}
}

package com.dm.study.cloud.vo;

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
 * <p>创建日期：2022年03月15日 17:31</p>
 * <p>类全名：com.dm.study.cloud.vo.QueryParams</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class QueryParams implements Serializable {
	private static final long   serialVersionUID = 6782265731168144010L;
	/**
	 * 分页查询，查询页码
	 */
	private              int    pageNum;
	/**
	 * 分页查询，每页数量
	 */
	private              int    pageSize;
	/**
	 * 排序字段
	 */
	private              String orderColumn;
	/**
	 * 排序规则(asc or desc)
	 */
	private              String rule;

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getOrderColumn() {
		return orderColumn;
	}

	public void setOrderColumn(String orderColumn) {
		this.orderColumn = orderColumn;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	@Override
	public String toString() {
		return "QueryParams{" + "pageNum=" + pageNum + ", pageSize=" + pageSize + ", orderColumn='" + orderColumn + '\'' + ", rule='" + rule + '\'' + '}';
	}
}

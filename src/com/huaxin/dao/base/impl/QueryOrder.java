package com.huaxin.dao.base.impl;

public class QueryOrder {

	public static final String ORDER_ASC = "asc";
	public static final String ORDER_DESC = "desc";
	private String name;
	private String orderBy;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public QueryOrder(String name, String orderBy) {
		this.name = name;
		this.orderBy = orderBy;
	}

	public static QueryOrder asc(String name) {
		return new QueryOrder(name, QueryOrder.ORDER_ASC);
	}

	public static QueryOrder desc(String name) {
		return new QueryOrder(name, QueryOrder.ORDER_DESC);
	}
}

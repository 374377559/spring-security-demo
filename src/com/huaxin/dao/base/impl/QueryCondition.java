package com.huaxin.dao.base.impl;

import java.util.LinkedList;
import java.util.List;

public class QueryCondition {
	// 操作符
	public static final String OPER_EQ = " = ";
	public static final String OPER_LE = " <= ";
	public static final String OPER_GE = " >= ";
	public static final String OPER_LIKE = " LIKE ";
	public static final String OPER_NULL = " IS NULL ";
	public static final String OPER_NONULL = " IS NOT NULL ";
	public static final String OPER_GT = " > ";
	public static final String OPER_LT = " < ";
	public static final String OPER_NE = " != ";
	public static final String OPER_IN = "in";

	public static final int IOPER_EQ = 0;
	public static final int IOPER_LE = 1;
	public static final int IOPER_GE = 2;
	public static final int IOPER_LIKE = 3;
	public static final int IOPER_NULL = 4;
	public static final int IOPER_NONULL = 5;
	public static final int IOPER_GT = 6;
	public static final int IOPER_LT = 7;
	public static final int IOPER_NE = 8;
	public static final int IOPER_IN = 9;

	// 数据类型
	public static final int DATA_STRING = 1;
	public static final int DATA_NUMBER = 2;
	public static final int DATA_DATE = 3;

	// 连接类型
	public static final int LINK_AND = 11;
	public static final int LINK_OR = 12;

	// 名-值对
	private String name;
	private Object value;
	private Object[] values;

	public Object[] getValues() {
		return values;
	}

	public void setValues(Object[] values) {
		this.values = values;
	}

	// 数据类型
	private Integer dataType;

	// 操作符
	private String operator;
	private int ioperator;

	public int getIoperator() {
		return ioperator;
	}

	public void setIoperator(int ioperator) {
		this.ioperator = ioperator;
	}

	private int linkType;

	public int getLinkType() {
		return linkType;
	}

	public void setLinkType(int linkType) {
		this.linkType = linkType;
	}

	List<QueryCondition> conditions = null;

	public List<QueryCondition> getConditions() {
		return conditions;
	}

	public void setConditions(List<QueryCondition> conditions) {
		this.conditions = conditions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public Integer getDataType() {
		return dataType;
	}

	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public QueryCondition() {
	}

	public QueryCondition(String name, Object value, String operator) {
		this.name = name;
		this.value = value;
		this.operator = operator;
	}

	public QueryCondition(String name, Object value, int ioperator) {
		this.name = name;
		this.value = value;
		this.ioperator = ioperator;
	}

	public QueryCondition(String name, Object[] values, String operator) {
		this.name = name;
		this.values = values;
		this.operator = operator;
	}

	public QueryCondition(String name, Object[] values, int ioperator) {
		this.name = name;
		this.values = values;
		this.ioperator = ioperator;
	}

	public static QueryCondition eq(String name, Object value) {
		return new QueryCondition(name, value, QueryCondition.IOPER_EQ);
	}

	public static QueryCondition le(String name, Object value) {
		return new QueryCondition(name, value, QueryCondition.IOPER_LE);
	}

	public static QueryCondition ge(String name, Object value) {
		return new QueryCondition(name, value, QueryCondition.IOPER_GE);
	}

	public static QueryCondition like(String name, Object value) {
		return new QueryCondition(name, value, QueryCondition.IOPER_LIKE);
	}

	public static QueryCondition isNull(String name, Integer dataType) {
		return new QueryCondition(name, "", QueryCondition.IOPER_NULL);
	}

	public static QueryCondition noNull(String name, Integer dataType) {
		return new QueryCondition(name, "", QueryCondition.IOPER_NONULL);
	}

	public static QueryCondition gt(String name, Object value) {
		return new QueryCondition(name, value, QueryCondition.IOPER_GT);
	}

	public static QueryCondition lt(String name, Object value) {
		return new QueryCondition(name, value, QueryCondition.IOPER_LT);
	}

	public static QueryCondition ne(String name, Object value) {
		return new QueryCondition(name, value, QueryCondition.IOPER_NE);
	}

	public static QueryCondition in(String name, Object[] values) {
		return new QueryCondition(name, values, QueryCondition.IOPER_IN);
	}

	public static QueryCondition or(QueryCondition... conditionTypes) {
		QueryCondition cond = new QueryCondition();
		cond.setLinkType(LINK_OR);

		List<QueryCondition> conditions = new LinkedList<QueryCondition>();
		for (QueryCondition c : conditionTypes) {
			conditions.add(c);
		}
		cond.setConditions(conditions);
		return cond;
	}

}

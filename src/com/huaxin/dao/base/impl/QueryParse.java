package com.huaxin.dao.base.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.huaxin.dao.base.PageModel;

public class QueryParse<T> {

	List<QueryCondition> conditions = new ArrayList<QueryCondition>();
	public QueryParse<T> add(QueryCondition condition) {
		this.conditions.add(condition);
		return this;
	}

	public List<Criterion> criterion() {
		List<Criterion> criterions = new ArrayList<Criterion>();

		int length = this.conditions.size();
		for (int i = 0; i < length; i++) {
			QueryCondition cond = this.conditions.get(i);
			if (cond.getLinkType() != QueryCondition.LINK_OR) {
				criterions.add(getCondition(cond));
			} else {
				Disjunction dj = Restrictions.disjunction();
				List<QueryCondition> ors = cond.getConditions();
				
				Criterion tCriterion = null;
				for (int j = 0; j < ors.size(); j++) {
					tCriterion = dj.add(getCondition(ors.get(j)));
				}
				if (tCriterion != null) {
					criterions.add(tCriterion);
				}
			}
		}

		return criterions;
	}



	private QueryOrder order;

	public QueryParse<T> addOrder(QueryOrder order) {
		this.order = order;
		return this;
	}

	public QueryOrder getOrder() {
		return order;
	}

	public void setOrder(QueryOrder order) {
		this.order = order;
	}

	private PageModel<T> pageModel;

	public PageModel<T> getPageModel() {
		return pageModel;
	}

	public void setPageModel(PageModel<T> pageModel) {
		this.pageModel = pageModel;
	}

	public QueryParse<T> addFetch(int pageNo, int pageSize) {
		this.pageModel = new PageModel<T>(pageNo, pageSize);
		return this;
	}

	private Criterion getCondition(QueryCondition condition) {
		if (condition != null) {
			int oper = condition.getIoperator();
			if (QueryCondition.IOPER_EQ == oper) {
				return Restrictions.eq(condition.getName(), condition.getValue());
			} else if (QueryCondition.IOPER_NE == oper) {
				return Restrictions.ne(condition.getName(), condition.getValue());
			} else if (QueryCondition.IOPER_GE == oper) {
				return Restrictions.ge(condition.getName(), condition.getValue());
			} else if (QueryCondition.IOPER_GT == oper) {
				return Restrictions.gt(condition.getName(), condition.getValue());
			} else if (QueryCondition.IOPER_LE == oper) {
				return Restrictions.le(condition.getName(), condition.getValue());
			} else if (QueryCondition.IOPER_LT == oper) {
				return Restrictions.lt(condition.getName(), condition.getValue());
			} else if (QueryCondition.IOPER_NULL == oper) {
				return Restrictions.isNull(condition.getName());
			} else if (QueryCondition.IOPER_NONULL == oper) {
				return Restrictions.isNotNull(condition.getName());
			} else if (QueryCondition.IOPER_LIKE == oper) {
				return Restrictions.like(condition.getName(), condition.getValue().toString(), MatchMode.START);
			} else if (QueryCondition.IOPER_IN == oper) {
				return Restrictions.in(condition.getName(), condition.getValues());
			}
		}
		return null;
	}
}

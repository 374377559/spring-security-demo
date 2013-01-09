package com.huaxin.dao.base;

import java.util.List;

import com.huaxin.dao.base.impl.QueryParse;
import com.huaxin.exception.ApplyException;

public interface BaseDao<T> {

	T get(Integer id);

	void save(T entity) throws ApplyException;

	void update(T entity) throws ApplyException;

	void delete(T entity) throws ApplyException;

	int count();

	List<T> findAll();

	PageModel<T> findAll(int pageNo, int PageSize);

	PageModel<T> search(QueryParse<T> qp);
}

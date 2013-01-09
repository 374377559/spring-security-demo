package com.huaxin.dao.base.impl;

import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.huaxin.dao.base.BaseDao;
import com.huaxin.dao.base.PageModel;
import com.huaxin.exception.ApplyException;

public abstract class HibernateBaseDao<T> extends HibernateDaoSupport implements BaseDao<T> {
	private static final Logger logger = Logger.getLogger("persistentClass");

	private Class<T> persistentClass;

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	public void setPersistentClass(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}

	@SuppressWarnings("unchecked")
	public HibernateBaseDao() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public T get(Integer id) {
		return (T) this.getHibernateTemplate().get(persistentClass, id);
	}

	public void save(T entity) throws ApplyException {
		try {
			this.getHibernateTemplate().save(entity);
		} catch (Exception e) {
			logger.error(e);
			throw new ApplyException(e);
		}
	}

	public void update(T entity) throws ApplyException {
		try {
			this.getHibernateTemplate().update(entity);
		} catch (Exception e) {
			logger.error(e);
			throw new ApplyException(e);
		}
	}

	public void delete(T entity) throws ApplyException {
		try {
			this.getHibernateTemplate().delete(entity);
		} catch (Exception e) {
			logger.error(e);
			throw new ApplyException(e);
		}
	}

	public int count() {
		return statistics();
	}

	public List<T> findAll() {
		return findByCriteria();
	}

	public PageModel<T> findAll(final int pageNo, final int pageSize) {
		return search(new QueryParse<T>().addFetch(pageNo, pageSize));
	}

	@SuppressWarnings("unchecked")
	public PageModel<T> search(final QueryParse<T> queryParse) {
		final PageModel<T> pm = new PageModel<T>();

		List<T> list = (List<T>) getHibernateTemplate().executeFind(
				new HibernateCallback<List<T>>() {
					public List<T> doInHibernate(Session session) throws HibernateException, SQLException {
						if (queryParse != null) {
							Criteria criteria = session.createCriteria(persistentClass);
							List<Criterion> criterions = queryParse.criterion();

							PageModel<T> pageModel = queryParse.getPageModel();
							if (pageModel != null) {
								int pageNo = pageModel.getPageNo();
								int pageSize = pageModel.getPageSize();
								criteria.setFirstResult((pageNo - 1) * pageSize);
								criteria.setMaxResults(pageSize);

								int count = statistics(criterions);
								pm.setTotal(count);
								pm.setPageNo(pageNo);
								pm.setPageSize(pageSize);
							}

							for (int i = 0; i < criterions.size(); i++) {
								criteria.add(criterions.get(i));
							}

							QueryOrder ord = queryParse.getOrder();
							if (ord != null) {
								if (ord.getOrderBy() != null && !"".equals(ord.getOrderBy())) {
									if (QueryOrder.ORDER_ASC.equals(ord.getOrderBy())) {
										criteria.addOrder(Order.asc(ord.getName()));
									} else {
										criteria.addOrder(Order.desc(ord.getName()));
									}
								}
							}
							return criteria.list();
						} else {
							return null;
						}
					}
				}
			);

		pm.setDatas(list);
		return pm;
	}

	public List<T> findByCriteria(final Criterion... criterion) {
		@SuppressWarnings("unchecked")
		List<T> list = getHibernateTemplate().executeFind(
				new HibernateCallback<List<T>>() {
					public List<T> doInHibernate(Session session) throws HibernateException, SQLException {
						Criteria criteria = session.createCriteria(persistentClass);
						for (Criterion c : criterion) {
							if (c != null) {
								criteria.add(c);
							}
						}
						return criteria.list();
					}
				}
			);
		return list;
	}
	
	public List<T> findByCriteria(final List<Criterion> criterion) {
		Criterion [] criterions = new Criterion[criterion == null ? 0 : criterion.size()];
		return findByCriteria(criterions);
	}

	// 统计件数
	public int statistics(final Criterion... criterion) {
		int sum = getHibernateTemplate().execute(
				new HibernateCallback<Integer>() {
					public Integer doInHibernate(Session session) throws HibernateException, SQLException {
						Criteria counts = session.createCriteria(persistentClass);
						if (criterion != null) {
							for (Criterion c : criterion) {
								counts.add(c);
							}
						}
						int count = ((Long) counts.setProjection(Projections.rowCount()).uniqueResult()).intValue();
						return count;
					}
				}
			);
		return sum;
	}
	public int statistics(final List<Criterion> criterion) {
		Criterion [] criterions = new Criterion[criterion == null ? 0 : criterion.size()];
		return statistics(criterions);
	}
}

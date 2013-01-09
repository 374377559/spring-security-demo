package com.huaxin.dao;

import com.huaxin.bean.Users;
import com.huaxin.dao.base.BaseDao;

public interface UsersDao extends BaseDao<Users> {
	public Users findByName(String name);
}

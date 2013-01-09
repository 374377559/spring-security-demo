package com.huaxin.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.huaxin.bean.Resources;
import com.huaxin.bean.Roles;
import com.huaxin.bean.Users;
import com.huaxin.dao.UsersDao;

public class MyUserDetailServiceImpl implements UserDetailsService {

	private UsersDao usersDao;

	public UsersDao getUsersDao() {
		return usersDao;
	}

	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}

	// 登录验证
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println(">>>>>>>>>>111<<<<<<<<<< username is " + username);
		
		//取得用户的权限
		Users users = this.usersDao.findByName(username);
		Collection<GrantedAuthority> grantedAuths = obtionGrantedAuthorities(users);

		// 封装成spring security的user
		User userdetail = new User(
				users.getAccount(), 
				users.getPassword(),
				true, 
				true, 
				true,
				true, 
				grantedAuths	//用户的权限
			);
		return userdetail;
	}

	// 取得用户的权限
	private Set<GrantedAuthority> obtionGrantedAuthorities(Users user) {
		List<Resources> resources = new ArrayList<Resources>();
		Set<Roles> roles = user.getRoles();
		for (Roles role : roles) {
			Set<Resources> tempRes = role.getResources();
			for (Resources res : tempRes) {
				resources.add(res);
			}
		}
		
		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
		for (Resources res : resources) {
			// TODO:ZZQ 用户可以访问的资源名称（或者说用户所拥有的权限） 注意：必须"ROLE_"开头
			// 关联代码：applicationContext-security.xml
			// 关联代码：com.huaxin.security.MySecurityMetadataSource#loadResourceDefine
			authSet.add(new SimpleGrantedAuthority("ROLE_" + res.getName()));
		}
		return authSet;
	}
}

package com.huaxin.bean;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
public class Users implements java.io.Serializable {

	private Integer id;
	private Integer enable;
	private String password;
	private String account;
	private Set<Roles> roles = new HashSet<Roles>(0);

	public Users() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEnable() {
		return this.enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Set<Roles> getRoles() {
		return roles;
	}

	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}

}
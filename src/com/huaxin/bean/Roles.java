package com.huaxin.bean;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
public class Roles implements java.io.Serializable {

	private Integer id;
	private Integer enable;
	private String name;
	private Set<Roles> roles = new HashSet<Roles>(0);
	private Set<Resources> resources = new HashSet<Resources>(0);

	public Roles() {
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@SuppressWarnings("rawtypes")
	public Set getRoles() {
		return roles;
	}

	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}

	public Set<Resources> getResources() {
		return resources;
	}

	public void setResources(Set<Resources> resources) {
		this.resources = resources;
	}

}
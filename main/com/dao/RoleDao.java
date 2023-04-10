package com.dao;

import java.util.List;

import com.entity.Role;

public interface RoleDao {

	public Role findRoleByName(String theRoleName);
	
	public List<Role> getRoles();
}

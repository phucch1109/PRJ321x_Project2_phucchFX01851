package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.RoleDao;
import com.entity.Role;


@Service
public class RoleServiceImpl implements RoleService{
	@Autowired
	RoleDao roleDao ;
	
	@Transactional
	@Override
	public List<Role> getRoles() {
		return roleDao.getRoles();
	}
}

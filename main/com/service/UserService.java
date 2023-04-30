package com.service;

import com.entity.Role;
import com.entity.User;
import com.subEntity.CrmUser;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    public User findByUserName(String userName);
    public void update(User user) ;
    public void save(CrmUser crmUser,int roleId, int companyId);
}

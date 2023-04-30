package com.dao;

import com.entity.User;

public interface UserDao {

    User findByUserName(String userName);
    
    void save(User user);
    void update(User theUser);
}

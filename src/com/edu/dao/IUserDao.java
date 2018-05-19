package com.edu.dao;

import com.edu.model.User;

public interface IUserDao {
    
    int insertUser(User user); // 用户注册
    
    User login(User user);
}

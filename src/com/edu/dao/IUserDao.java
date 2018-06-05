package com.edu.dao;

import com.edu.model.Card;
import com.edu.model.User;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface IUserDao {
    
    int insertUser(User user); // 用户注册
    
    User login(User user);  // 用户登录

    List<User> findAll(HttpSession session); // 列出全部记录
    
    int remove(User user);  // 将用户放入回收站
    
    int reset(User user);  // admin重置用户密码
    
    int editPwd(User user, String newPwd);  // 修改用户密码
    
    int checkUser(String username);
    
    
}

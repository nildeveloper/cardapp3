package com.edu.controller.user;

import com.edu.dao.IUserDao;
import com.edu.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 检查用户是否存在
 */
public class CheckServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");        
        String username = request.getParameter("username");
        IUserDao userDao = new UserDao();
        int isExit = userDao.checkUser(username);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(String.valueOf(isExit));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

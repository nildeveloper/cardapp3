package com.edu.controller.user;

import com.edu.dao.UserDao;
import com.edu.model.User;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class Register extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int authrity = Integer.parseInt(request.getParameter("authrity"));
        User user  = new User(username,password,authrity);
        UserDao userDao = new UserDao();
        int n = userDao.insertUser(user);
        if (n > 0) {
            response.sendRedirect("login.jsp");
        } else {
            request.setAttribute("message", "注册失败！");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }   
}

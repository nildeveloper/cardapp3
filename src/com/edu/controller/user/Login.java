package com.edu.controller.user;

import com.edu.dao.UserDao;
import com.edu.model.User;
import com.edu.utils.MD5Util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password"); // 明文密码
        String checkCode = request.getParameter("checkcode").toUpperCase(); // 输入验证码不区分大小写
        String remeberMe = request.getParameter("rememberMe");
        HttpSession session = request.getSession();
        String realCode = (String) session.getAttribute("realCode");  // 获取session域中验证码
        String passwordMd5 = MD5Util.MD5(password);  // 加密后的密码
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordMd5);
        UserDao userDao = new UserDao();
        User user1 = userDao.login(user);
        if (checkCode.equals(realCode)) {
            if (user1 != null) {
                session.setAttribute("userId", user1.getId());
                session.setAttribute("user", user1);
                session.setAttribute("username", user1.getUsername());
                if (user1.getUsername().equals("admin")) {  
                    // 如果登录用户是管理员，则页面跳转到管理员管理页面
                    session.setAttribute("userType","1");
                    response.sendRedirect("frame");
                } else {
                    // 否则跳转到用户管理页面
                    session.setAttribute("userType","2");
                    response.sendRedirect("frame");
                }
            } else {
                // 密码错误，设置错误提示信息，页面转发到登录页面
                request.setAttribute("message", "用户名或密码错误！");  // 验证失败
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } else {
            // 验证码错误，设置错误提示信息，页面转发到登录页面
            request.setAttribute("message", "验证码错误！");  // 验证失败
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

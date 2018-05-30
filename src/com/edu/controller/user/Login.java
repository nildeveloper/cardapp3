package com.edu.controller.user;

import com.edu.dao.UserDao;
import com.edu.model.User;

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
        String password = request.getParameter("password");
        String checkCode = request.getParameter("checkcode");
        HttpSession session = request.getSession();
        String realCode = (String) session.getAttribute("realCode");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        UserDao userDao = new UserDao();
        User user1 = userDao.login(user);
        if (checkCode.equals(realCode)) {
            if (user1 != null) {
                session.setAttribute("userId", user1.getId());
                session.setAttribute("user", user1);
                session.setAttribute("username", user1.getUsername());
                if (user1.getUsername().equals("admin")) {
                    request.getRequestDispatcher("frame_admin.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("frame").forward(request, response);
                }
            } else {
                request.setAttribute("message", "用户名或密码错误！");  // 验证失败
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("message", "验证码错误！");  // 验证失败
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

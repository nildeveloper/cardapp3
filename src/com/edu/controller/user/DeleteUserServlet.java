package com.edu.controller.user;

import com.edu.dao.UserDao;
import com.edu.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        int id = Integer.parseInt(request.getParameter("id"));
        User user = new User();
        user.setId(id);
        UserDao userDao = new UserDao();
        int n = userDao.remove(user);
        response.setCharacterEncoding("utf-8");
        if (n > 0) {
            response.getWriter().write("删除成功！");
        } else {
            response.getWriter().write("删除失败！");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

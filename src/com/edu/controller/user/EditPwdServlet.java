package com.edu.controller.user;

import com.edu.dao.UserDao;
import com.edu.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 用户密码修改
 */
public class EditPwdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        int id = (int) session.getAttribute("userId");
        String newPwd = request.getParameter("password2");
        User user = new User();
        user.setId(id);
        UserDao userDao = new UserDao();
        int n = userDao.editPwd(user,newPwd);
        response.setCharacterEncoding("utf-8");
        if (n > 0) {
            response.getWriter().write("修改成功！");
        } else {
            response.getWriter().write("修改失败！");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

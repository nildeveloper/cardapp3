package com.edu.controller.card;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 页面跳转到frame.jsp
 */
public class FrameServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userType = (String) session.getAttribute("userType");
        // 若登陆用户是admin
        if (userType.equals("1")) {
            request.getRequestDispatcher("frame_admin.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("frame.jsp").forward(request, response);
        }
    }
}

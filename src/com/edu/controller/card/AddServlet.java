package com.edu.controller.card;

import com.edu.dao.CardDao;
import com.edu.model.Card;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 名片添加
 */
public class AddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        String name = request.getParameter("name");
        String tel = request.getParameter("tel");
        String address =request.getParameter("address");
        String email = request.getParameter("email");
        int userId = (int) session.getAttribute("userId");
        Card card = new Card(0,name,tel,address,email,userId,0);
        CardDao cardDao = new CardDao();
        int n = cardDao.create(card);
        if (n > 0) {
            request.getRequestDispatcher("list").forward(request,response);
        } else  {
            request.setAttribute("message","名片插入失败！");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }   
}

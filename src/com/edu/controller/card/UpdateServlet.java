package com.edu.controller.card;

import com.edu.dao.CardDao;
import com.edu.model.Card;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * 名片更新
 */
public class UpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String tel = request.getParameter("tel");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        int userId = Integer.parseInt(request.getParameter("userId"));
        int isDelete = Integer.parseInt(request.getParameter("isDelete"));
        Card card = new Card(id,name,tel,address,email,userId,isDelete);
        CardDao cardDao = new CardDao();
        int n = cardDao.update(card);
        if (n > 0) {
            request.setAttribute("message","更新成功");
            request.getRequestDispatcher("list").forward(request,response);
        } else {
            request.setAttribute("message","更新失败");
            request.getRequestDispatcher("list").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

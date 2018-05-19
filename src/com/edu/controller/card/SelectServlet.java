package com.edu.controller.card;

import com.edu.dao.CardDao;
import com.edu.model.Card;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * 卡片记录模糊筛选
 */
public class SelectServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String name = request.getParameter("name");
        String tel = request.getParameter("tel");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        Card card = new Card(0,name,tel,address,email,0,0);
        CardDao cardDao = new CardDao();
        HttpSession session = request.getSession();
        List<Card> cardList = cardDao.selectCard(card,session);
        request.setAttribute("cards",cardList);  // 将list放入request域S
        request.getRequestDispatcher("list.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

package com.edu.controller.trash;

import com.edu.dao.TrashDao;
import com.edu.model.Card;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ListTrashServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        TrashDao trashDao = new TrashDao();
        List<Card> cards = trashDao.findAll(session);
        request.setAttribute("cards",cards);  // 将list放入request域
        request.getRequestDispatcher("trash.jsp").forward(request,response); // 跳转到list页面显示数据
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

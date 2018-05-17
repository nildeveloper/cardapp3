package com.edu.controller.card;

import com.edu.dao.CardDao;
import com.edu.model.Card;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 查找名片
 */
public class FindServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        int id = Integer.parseInt(request.getParameter("id"));
        Card card = new Card();
        card.setId(id);
        CardDao cardDao = new CardDao();
        Card card1 = cardDao.find(card);
        JSONObject jobj = JSONObject.fromBean(card1);  // 转换为json对象返回
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(jobj.toString());// 将数据返回
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

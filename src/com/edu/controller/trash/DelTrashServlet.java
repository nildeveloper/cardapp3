package com.edu.controller.trash;

import com.edu.dao.TrashDao;
import com.edu.model.Card;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 回收站彻底删除
 */
public class DelTrashServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        int id = Integer.parseInt(request.getParameter("id"));
        Card card = new Card();
        card.setId(id);
        TrashDao trashDao = new TrashDao();
        int n = trashDao.delete(card);
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

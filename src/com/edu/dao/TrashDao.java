package com.edu.dao;

import com.edu.model.Card;
import com.edu.utils.DBConnection;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrashDao implements ITrashDao {

    protected static String RECOVER_SQL = "update tb_card set isdelete = 0 where id = ?";
    protected static String DELETE_SQL = "delete from tb_card where id = ?";

    /**
     * 从回收站彻底删除
     * @param card
     * @return
     */
    @Override
    public int delete(Card card) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int row = 0;
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(DELETE_SQL);
            pstmt.setInt(1,card.getId());
            row = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeDB(conn,pstmt,rs);
        }
        return row;
    }

    /**
     * 查出回收站全部名片记录
     * @param session
     * @return
     */
    @Override
    public List<Card> findAll(HttpSession session) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Card> cards = new ArrayList<>();
        String LIST_SQL = "select * from tb_card where user_id=" + session.getAttribute("userId") + " and isdelete = 1";
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(LIST_SQL);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String tel = rs.getString("tel");
                String address = rs.getString("address");
                String email = rs.getString("email");
                int userId = rs.getInt("user_id");
                int isDelete = rs.getInt("isdelete");
                Card card = new Card(id,name,tel,address,email,userId,isDelete);
                cards.add(card);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeDB(conn,pstmt,rs);
        }
        return cards;
    }

    /**
     * 将回收站数据恢复
     * @param card
     * @return
     */
    @Override
    public int recover(Card card) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int row = 0;
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(RECOVER_SQL);
            pstmt.setInt(1,card.getId());
            row = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeDB(conn,pstmt,rs);
        }
        return row;
    }

    /**
     * 模糊查询回收站名片数据
     * @param card
     * @param session
     * @return
     */
    @Override
    public List<Card> selectTrash(Card card, HttpSession session) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Card> cards = new ArrayList<>();
        int userId1 = (int) session.getAttribute("userId");
        String sql = "select * from tb_card WHERE  user_id=? and isdelete=1 or name=?" +
                " or tel=? or address=? or email=?";
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,userId1);
            pstmt.setString(2,card.getName());
            pstmt.setString(3,card.getTel());
            pstmt.setString(4,card.getAddress());
            pstmt.setString(5,card.getEmail());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String tel = rs.getString("tel");
                String address = rs.getString("address");
                String email = rs.getString("email");
                int userId = rs.getInt("user_id");
                int isDelete = rs.getInt("isdelete");
                Card card1 = new Card(id,name,tel,address,email,userId,isDelete);
                cards.add(card1);
            }

        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeDB(conn,pstmt,rs);
        }
        return cards;
    }
}

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

public class CardDao implements ICardDao {
    
    protected static final String CARD = "name,tel,address,email,user_id";
    protected static String INSERT_SQL = "insert into tb_card(" + CARD + ")" + " values(?, ?, ?, ?, ?)";
    protected static String REMOVE_SQL = "update tb_card set isdelete = 1 where id = ?";
    protected static String SELECT_SQL = "select * from tb_card where id = ?";
    protected static String UPDATE_SQL = "update tb_card set " + "name=?, tel=?, address=?, email=?,user_id=? where id=?";
    
    /**
     * 插入名片记录
     * @param card
     * @return
     * @throws Exception
     */
    @Override
    public int create(Card card){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int row = 0;
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(INSERT_SQL);
            pstmt.setString(1,card.getName());
            pstmt.setString(2,card.getTel());
            pstmt.setString(3,card.getAddress());
            pstmt.setString(4,card.getEmail());
            pstmt.setInt(5, card.getUserId());
            row = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeDB(conn,pstmt,rs);
        }
        return row;
    }

    /**
     * 名片记录放入回收站
     * @param card
     * @return
     * @throws Exception
     */
    @Override
    public int remove(Card card) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int row = 0;
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(REMOVE_SQL);
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
     * 查找名片记录
     * @param card
     * @return
     */
    @Override
    public Card find(Card card) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Card card1 = null;
        try {
            int id = card.getId();
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(SELECT_SQL);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String tel = rs.getString("tel");
                String address = rs.getString("address");
                String email = rs.getString("email");
                int userId = rs.getInt("user_id");
                int isDelete = rs.getInt("isdelete");
                card1 = new Card(id, name, tel, address, email, userId, isDelete);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeDB(conn, pstmt, rs);
        }
        return card1;
    }

    /**
     * 更新名片记录
     * @param card
     * @return
     */
    @Override
    public int update(Card card)  {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int row = 0;
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(UPDATE_SQL);
            pstmt.setString(1,card.getName());
            pstmt.setString(2,card.getTel());
            pstmt.setString(3,card.getAddress());
            pstmt.setString(4,card.getEmail());
            pstmt.setInt(5, card.getUserId());
            pstmt.setInt(6,card.getId());
            row = pstmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeDB(conn,pstmt,rs);
        }
        return row;
    }

    /**
     * 查询所有名片记录
     * @return
     */
    @Override
    public List<Card> findAll(HttpSession session) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Card> cards = new ArrayList<>();
//        System.out.println(session.getAttribute("userId"));
        String LIST_SQL = "select * from tb_card where user_id=" + session.getAttribute("userId") + " and isdelete = 0";
//        System.out.println(LIST_SQL);
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
     * 名片模糊查询
     * @param card
     * @param session
     * @return
     */
    @Override
    public List<Card> selectCard(Card card, HttpSession session) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Card> cards = new ArrayList<>();
        int userId1 = (int) session.getAttribute("userId");
        String s = " 1 = 1";
        if (!card.getName().equals("")) {
            s+=" and name like'%" + card.getName()+"%'";
        }
        if (!card.getTel().equals("")) {
            s+=" and tel like '%" + card.getTel() + "'";
        }
        if (!card.getAddress().equals("")) {
            s+= " and address like'%" + card.getAddress()+"%'";
        }
        if (!card.getEmail().equals("")) {
            s+= " and email like'%" + card.getEmail() + "%'";
        }
        String sql = "select * from tb_card WHERE  user_id=? and isdelete=0 and";
        try {
            sql += s;
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,userId1);
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

package com.edu.dao;

import com.edu.model.User;
import com.edu.utils.DBConnection;
import com.edu.utils.MD5Util;
import com.edu.utils.TableContants;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements IUserDao{
    
    protected static final String USER = "username,password,authrity";
    
    protected static final String INSERT_SQL = "insert into " + TableContants.USERTABLE 
            + "(" + USER + ")" + " values(?,?,?)";
    
    protected static final String SELECT_SQL = "select * from " + TableContants.USERTABLE + " where username = ? and password = ?";

    /**
     * 用户注册
     * @param user
     * @return
     */
    @Override
    public int insertUser(User user) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int row = 0;
            try {
                conn = DBConnection.getConnection();
                pstmt = conn.prepareStatement(INSERT_SQL);
                pstmt.setString(1,user.getUsername());
                pstmt.setString(2,user.getPassword());
                pstmt.setInt(3,user.getAuthrity());
                row = pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();;
        } finally {
            DBConnection.closeDB(conn,pstmt,rs);
        }
        return row;
    }

    /**
     * 用户登陆 
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        User user1 = null;
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(SELECT_SQL);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            rs = pstmt.executeQuery();
            while (rs.next()) {  // 根据用户名和密码从数据库查询用户，若存在，则将用户返回
                user1 = new User();
                user1.setId(rs.getInt("id"));
                user1.setUsername(rs.getString("username"));
                user1.setPassword(rs.getString("password"));
                user1.setAuthrity(rs.getInt("authrity"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeDB(conn, pstmt, rs);
        }
        return user1;
    }

    /**
     * 查询全部用户
     * @param session
     * @return
     */
    @Override
    public List<User> findAll(HttpSession session) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<User> users = new ArrayList<>();
//        System.out.println(session.getAttribute("userId"));
        String LIST_SQL = "select * from tb_user ";
//        System.out.println(LIST_SQL);
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(LIST_SQL);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                User user = new User(id,username,password);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeDB(conn,pstmt,rs);
        }
        return users;
    }

    /**
     * 删除用户
     * @param user
     * @return
     */
    @Override
    public int remove(User user) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String DELETE_SQL = "delete from tb_user where id = ?";
        int row = 0;
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(DELETE_SQL);
            pstmt.setInt(1,user.getId());
            row = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeDB(conn,pstmt,rs);
        }
        return row;
    }

    /**
     * 重置用户密码
     * @param user
     * @return
     */
    @Override
    public int reset(User user) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String RESET_SQL = "update tb_user set password='4QrcOUm6Wau+VuBX8g+IPg==' where id = ?";
        int row = 0;
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(RESET_SQL);
            pstmt.setInt(1,user.getId());
            row = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeDB(conn,pstmt,rs);
        }
        return row;
    }

    /**
     * 用户密码修改
     * @param user
     * @param newPwd
     * @return
     */
    @Override
    public int editPwd(User user, String newPwd) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "update tb_user set password = ? where id = ?";
        String md5Pwd = MD5Util.MD5(newPwd);
        int row = 0;
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,md5Pwd);
            pstmt.setInt(2,user.getId());
            row = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeDB(conn,pstmt,rs);
        }
        return row;
    }
}

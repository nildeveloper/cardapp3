package com.edu.dao;

import com.edu.model.User;
import com.edu.utils.DBConnection;
import com.edu.utils.TableContants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}

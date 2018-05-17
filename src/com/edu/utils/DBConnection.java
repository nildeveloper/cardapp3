package com.edu.utils;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public final class DBConnection {
    
     private static String driverClass;
     private static String user;
     private static String password;
     private static  String url;
     
     // 静态代码块，装入类时执行，且仅执行一次
     static {
         try {
             InputStream in = DBConnection.class.getClassLoader()
                     .getResourceAsStream("dbconfig.properties");
             Properties properties = new Properties();
             properties.load(in);
             driverClass = properties.getProperty("driverClass");
             user = properties.getProperty("user");
             password = properties.getProperty("password");
             url = properties.getProperty("jdbcUrl");
             Class.forName(driverClass);
         }catch (Exception e) {
             e.printStackTrace();
         }
     }
     
     // 获取connection对象
     public static Connection getConnection() {
         Connection conn = null;
         try {
             conn = DriverManager.getConnection(url,user,password);
         } catch (SQLException e) {
             e.printStackTrace();
         }
         return conn;
     }
     
     // 资源释放
     public static void closeDB(Connection conn, PreparedStatement pstmt, ResultSet rs) {
         try {
             if (rs != null) {
                 rs.close();
             }
             if (pstmt != null) {
                 pstmt.close();
             }
             if (conn != null) {
                 conn.close();
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }
     }
     
}

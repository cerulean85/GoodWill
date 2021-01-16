package com.kkennib;

import javax.servlet.ServletException;
import java.sql.*;
import java.util.Properties;

public class DBConn {

    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;

    public DBConn() throws ServletException, SQLException {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {
            throw new ServletException(e);
        } finally {
        }

        Properties jdbcProperties = new Properties();
        jdbcProperties.setProperty("user", "root");
        jdbcProperties.setProperty("password", "123456");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/goodwill?serverTimezone=UTC", jdbcProperties);

    }

    public void updateState_Paused(String topicName) throws SQLException {
        String sql = "UPDATE work_state SET current_state = ?, update_date = NOW() WHERE topic_name = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, "paused");
        pstmt.setString(2, topicName);
        pstmt.executeUpdate();
        pstmt.close();
    }

    public void updateState_Terminated(String topicName) throws SQLException {
        String sql = "UPDATE work_state SET current_state = ?, update_date = NOW() WHERE topic_name = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, "terminated");
        pstmt.setString(2, topicName);
        pstmt.executeUpdate();
        pstmt.close();
    }

    public void close() throws SQLException {
        conn.close();
    }
}

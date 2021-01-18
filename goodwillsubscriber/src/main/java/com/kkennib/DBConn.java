package com.kkennib;

import com.kkennib.keyword.KeywordFormat;

import javax.servlet.ServletException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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

    public int insertWorkGroup(String groupId) throws SQLException {
        String sql = "INSERT FROM work_group(group_id, current_state) VALUES (?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, groupId);
        pstmt.setString(2, "enrolled");
        int res = pstmt.executeUpdate();
        pstmt.close();

        return res;
    }

    public List<KeywordFormat> getKeywordSet(String groupId) throws SQLException {
        String sql = "SELECT * FROM work_state WHERE group_id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, groupId);

        List<KeywordFormat> rsArr = new ArrayList<>();
        rs= pstmt.executeQuery();
        while(rs.next()) {
            KeywordFormat kwdFormat = new KeywordFormat();
            kwdFormat.setSiteType(rs.getString("site_type"));
            kwdFormat.setKeyword(rs.getString("keyword"));
            kwdFormat.setStartDate(rs.getString("start_date"));
            kwdFormat.setEndDate(rs.getString("end_date"));
            kwdFormat.setTopicName(rs.getString("topic_name"));
            kwdFormat.setGroupId(rs.getString("group_id"));
            kwdFormat.setCurrentState(rs.getString("current_state"));
            rsArr.add(kwdFormat);
        }
        rs.close();
        pstmt.close();

        return rsArr;
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

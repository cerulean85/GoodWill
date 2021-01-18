package com.kkennib;

import com.kkennib.consumer.ConsumerDaemon;
import com.kkennib.keyword.KeywordFormat;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import javax.jms.ConnectionFactory;
import javax.servlet.ServletException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DBConn {

    private Connection conn = null;
    private ResultSet rs = null;

    private static DBConn dbConn = null;
    public static DBConn getInstance() throws ServletException, SQLException, ClassNotFoundException {
        if(dbConn == null) {
            dbConn = new DBConn();
        }
        return dbConn;
    }

    public DBConn() throws ServletException, SQLException, ClassNotFoundException {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {
            throw new ServletException(e);
        } finally {
        }

//        Properties jdbcProperties = new Properties();
//        jdbcProperties.setProperty("user", "root");
//        jdbcProperties.setProperty("password", "123456");
//        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/goodwill?serverTimezone=UTC", jdbcProperties);

//        https://hsp1116.tistory.com/8
        String jdbcUrl = "jdbc:mysql://localhost:3306/goodwill?serverTimezone=UTC";
        String username = "root";
        String pwd = "123456";

        DriverManagerConnectionFactory connFactory =  new DriverManagerConnectionFactory(jdbcUrl, username, pwd);
        PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(connFactory, null);
        poolableConnectionFactory.setValidationQuery("select 1");

        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setTimeBetweenEvictionRunsMillis(1000L * 60L * 1L);
        poolConfig.setTestWhileIdle(true);
        poolConfig.setMinIdle(4);
        poolConfig.setMaxTotal(50);

        GenericObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolableConnectionFactory, poolConfig);
        poolableConnectionFactory.setPool(connectionPool);
        Class.forName("org.apache.commons.dbcp2.PoolingDriver");

        PoolingDriver driver = (PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:");
        driver.registerPool("cp", connectionPool);

    }

    public int insertWorkGroup(String groupId) throws SQLException {
        connect();
        String sql = "INSERT FROM work_group(group_id, current_state) VALUES (?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, groupId);
        pstmt.setString(2, WorkState.ENROLLED);
        int res = pstmt.executeUpdate();
        pstmt.close();
        close();
        return res;
    }

    public List<KeywordFormat> getKeywordSet(String groupId) throws SQLException {
        connect();
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
            kwdFormat.setFinishedWorkCount(rs.getInt("finished_work_count"));
            kwdFormat.setTotalWorkCount(rs.getInt("total_work_count"));
            rsArr.add(kwdFormat);
        }
        rs.close();
        pstmt.close();
        close();
        return rsArr;
    }

    public List<KeywordFormat> getOffset(String groupId) throws SQLException {
        connect();
        String sql = "SELECT * FROM work_state WHERE group_id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, groupId);

        List<KeywordFormat> rsArr = new ArrayList<>();
        rs= pstmt.executeQuery();
        while(rs.next()) {
            KeywordFormat kwdFormat = new KeywordFormat();
            kwdFormat.setFinishedWorkCount(rs.getInt("finished_work_count"));
            kwdFormat.setTotalWorkCount(rs.getInt("total_work_count"));
            rsArr.add(kwdFormat);
        }
        rs.close();
        pstmt.close();
        close();
        return rsArr;
    }

    public KeywordFormat getKeyword(String topicName) throws SQLException {
        connect();
        String sql = "SELECT * FROM work_state WHERE topic_name = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, topicName);

        rs= pstmt.executeQuery();
        while(rs.next()) {
            KeywordFormat kwdFormat = new KeywordFormat();
            kwdFormat.setFinishedWorkCount(rs.getInt("current_state"));
            kwdFormat.setFinishedWorkCount(rs.getInt("finished_work_count"));
            kwdFormat.setTotalWorkCount(rs.getInt("total_work_count"));
            return kwdFormat;
        }
        rs.close();
        pstmt.close();
        close();
        return new KeywordFormat();
    }

    public void updateState_Paused(String topicName) throws SQLException {
        connect();
        String sql = "UPDATE work_state SET current_state = ?, update_date = NOW() WHERE topic_name = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, WorkState.PAUSED);
        pstmt.setString(2, topicName);
        pstmt.executeUpdate();
        pstmt.close();
        close();
    }

    public void updateState_Terminated(String topicName) throws SQLException {
        connect();
        String sql = "UPDATE work_state SET current_state = ?, update_date = NOW() WHERE topic_name = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, WorkState.TERMINATED);
        pstmt.setString(2, topicName);
        pstmt.executeUpdate();
        pstmt.close();
        close();
    }

    public List<KeywordFormat> getWorkTable() throws SQLException {
        connect();
        String sql = "SELECT * FROM work_state ORDER BY group_id";
        PreparedStatement pstmt = conn.prepareStatement(sql);
//        pstmt.setString(1, groupId);

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
            kwdFormat.setFinishedWorkCount(rs.getInt("finished_work_count"));
            kwdFormat.setTotalWorkCount(rs.getInt("total_work_count"));
            rsArr.add(kwdFormat);
        }
        rs.close();
        pstmt.close();
        close();
        return rsArr;
    }

    private void connect() throws SQLException {
        conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:cp");
    }
    private void close() throws SQLException {
        conn.close();
    }
}

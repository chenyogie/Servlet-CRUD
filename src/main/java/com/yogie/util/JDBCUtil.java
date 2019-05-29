package com.yogie.util;

import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @program: BaseDemo
 * @Date: 2019/5/20 9:39
 * @Author: Chenyogie
 * @Description:
 */
public enum JDBCUtil {
    instance;
    public Connection getConn(){
        Properties p = new Properties();
        Connection conn = null;
        try {
            p.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
            DataSource dataSource = BasicDataSourceFactory.createDataSource(p);
            conn = dataSource.getConnection();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void close(Connection conn, Statement statement, ResultSet rs){
        try {
            if(rs!=null)rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(statement!=null)statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(conn!=null)conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

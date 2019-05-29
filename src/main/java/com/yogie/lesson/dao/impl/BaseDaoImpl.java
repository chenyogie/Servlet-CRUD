package com.yogie.lesson.dao.impl;

import com.yogie.lesson.dao.BaseDao;
import com.yogie.lesson.domain.Student;
import com.yogie.lesson.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: BaseDemo
 * @Date: 2019/5/23 16:16
 * @Author: Chenyogie
 * @Description:
 */
public class BaseDaoImpl implements BaseDao {
    @Override
    public List<Student> execute(String sql, Object... params) {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<Student> list = new ArrayList<>();
        try {
            conn = JDBCUtil.instance.getConn();
            statement = conn.prepareStatement(sql);
            for (int i = 1; i <= params.length; i++) {
                statement.setObject(i, params[i - 1]);
            }
            System.out.println(statement.toString());
            if (sql.contains("select")) {
                rs = statement.executeQuery();
                while (rs.next()) {
                    list.add(new Student(rs.getLong("id"), rs.getLong("grade_id"), rs.getString("username"), rs.getString("password"), rs.getInt("age"), rs.getBoolean("sex"), rs.getString("intro"), rs.getBigDecimal("account"),rs.getString("headimg")));
                }
            } else {
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.instance.close(conn, statement, rs);
        }
        return list;
    }
}

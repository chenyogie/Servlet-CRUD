package com.yogie.dao.impl;

import com.yogie.dao.StudentDao;
import com.yogie.domain.Student;
import com.yogie.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @program: BaseDemo
 * @Date: 2019/5/20 10:14
 * @Author: Chenyogie
 * @Description:
 */
public class  StudentDaoImpl extends BaseDaoImpl implements StudentDao {

    @Override
    public void delById(Long id) {
        String sql = "delete from student where id=?";
        super.execute(sql, id);
    }

    @Override
    public void add(Student stu) {
        String sql = "insert into student(grade_id,username,password,age,sex,intro,account,headimg)values(?,?,?,?,?,?,?,?)";
        super.execute(sql, stu.getGrade_id(), stu.getUsername(), stu.getPassword(), stu.getAge(), stu.isSex(), stu.getIntro(), stu.getAccount(),stu.getHeadimg());
    }

    @Override
    public Student queryOne(long id) {
        String sql = "select * from student where id=?";
        List<Student> list = super.execute(sql,id);
        return list.get(0);
    }

    @Override
    public void update(Student stu) {
        String sql = "update student set grade_id=?,username=?,password=?,age=?,sex=?,intro=?,account=?,headimg=? where id=?";
        super.execute(sql,stu.getGrade_id(),stu.getUsername(),stu.getPassword(),stu.getAge(),stu.isSex(),stu.getIntro(),stu.getAccount(),stu.getHeadimg(),stu.getId());
    }

    @Override
    public List<Student> queryPage(Integer currentPage,Integer pageSize,String key){
        StringBuffer sql = new StringBuffer();
        sql.append("select * from student where 1=1 ");
        if(key!=null && (!"".equals(key))){
            sql.append(" and username like'%"+key+"%' ");
        }
        sql.append(" limit ?,?");
        Integer startLine = (currentPage-1)*pageSize;
        return super.execute(sql.toString(),startLine,pageSize);
    }

    @Override
    public Integer queryCount(String key) {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        Integer count = 0;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT COUNT(*) AS COUNT FROM student where 1=1 ");
            if(key!=null && (!"".equals(key))){
                sql.append(" and username like'%"+key+"%' ");
            }
            conn = JDBCUtil.instance.getConn();
            statement = conn.prepareStatement(sql.toString());
            rs = statement.executeQuery();
            while (rs.next()) {
                count = rs.getInt("COUNT");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.instance.close(conn, statement, rs);
        }
        return count;
    }

    @Override
    public Student queryByUsernameAndPassword(String username,String password) {
        String sql = "select * from student where username=? and password=?";
        List<Student> list = super.execute(sql, username, password);
        //即是list集合中没有数据，但是还是有地址值的，所以判断条件不能直接是：list==null
        if(list.size()==0){
            return null;
        }else{
            return list.get(0);
        }
    }
}

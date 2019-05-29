package com.yogie.dao;

import com.yogie.domain.Student;

import java.util.List;

/**
 * @program: BaseDemo
 * @Date: 2019/5/20 10:05
 * @Author: Chenyogie
 * @Description:
 */
public interface StudentDao {

    void delById(Long id);

    void add(Student stu);

    Student queryOne(long id);

    void update(Student stu);

    //分页查询
    List<Student> queryPage(Integer currentPage, Integer pageSize, String key);

    //查询总数
    Integer queryCount(String key);

    Student queryByUsernameAndPassword(String username, String password);
}

package com.yogie.lesson.dao;

import com.yogie.lesson.domain.Student;

import java.util.List;

/**
 * @program: BaseDemo
 * @Date: 2019/5/23 16:14
 * @Author: Chenyogie
 * @Description:
 */
public interface BaseDao {
    List<Student> execute(String sql, Object... params);
}

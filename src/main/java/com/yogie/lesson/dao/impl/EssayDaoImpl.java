package com.yogie.lesson.dao.impl;

import com.yogie.lesson.dao.EssayDao;
import com.yogie.lesson.domain.Essay;

/**
 * @program: BaseDemo
 * @Date: 2019/5/28 1:06
 * @Author: Chenyogie
 * @Description:
 */
public class EssayDaoImpl extends BaseDaoImpl implements EssayDao {

    @Override
    public void add(Essay essay) {
        String sql = "insert into essay (id,stu_id,title,content) values (?,?,?,?)";
        super.execute(sql, essay.getId(),essay.getStu_id(),essay.getTitle(),essay.getContent());
    }
}

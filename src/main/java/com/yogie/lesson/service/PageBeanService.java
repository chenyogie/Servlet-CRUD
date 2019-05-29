package com.yogie.lesson.service;

import com.yogie.lesson.dao.StudentDao;
import com.yogie.lesson.dao.impl.StudentDaoImpl;
import com.yogie.lesson.domain.PageBean;

/**
 * @program: BaseDemo
 * @Date: 2019/5/24 17:53
 * @Author: Chenyogie
 * @Description:
 */
public class PageBeanService {

    /**
     * 将当前页、分页的步长封装到PageBean中
     * 将模糊查询关键字查询总条数，将总记录数封装到PageBean中
     * @param currentPage 当前页
     * @param pageSize 分页的步长
     * @param key 模糊查询的关键字
     * @return 返回PageBean
     */
    public PageBean queryPageBean(Integer currentPage,Integer pageSize,String key){
        PageBean pageBean = new PageBean();
        //设置当前页
        pageBean.setCurrentPage(currentPage);
        //设置每一页的大小
        pageBean.setPageSize(pageSize);
        StudentDao dao = new StudentDaoImpl();
        //设置总记录数
        pageBean.setTotalCount(dao.queryCount(key));
        //将查询出来的记录封装到PageBean中的data字段中
        pageBean.setData(dao.queryPage(pageBean.getCurrentPage(),pageBean.getPageSize(),key));
        return pageBean;
    }
}

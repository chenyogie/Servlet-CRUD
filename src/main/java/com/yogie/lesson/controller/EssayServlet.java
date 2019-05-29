package com.yogie.lesson.controller;

import com.yogie.lesson.dao.EssayDao;
import com.yogie.lesson.dao.impl.EssayDaoImpl;
import com.yogie.lesson.domain.Essay;
import com.yogie.lesson.util.BeanUtil;
import com.yogie.lesson.util.UUIDUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: BaseDemo
 * @Date: 2019/5/28 22:30
 * @Author: Chenyogie
 * @Description:
 */
@WebServlet("/essay")
public class EssayServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Essay essay = BeanUtil.getBean(req, Essay.class);
        essay.setId(UUIDUtil.getUUID());
        //应该根据当前登录的用户来插入数据
        essay.setStu_id(1);
        EssayDao dao = new EssayDaoImpl();
        dao.add(essay);
        resp.sendRedirect("/xx/index.html");
    }
}

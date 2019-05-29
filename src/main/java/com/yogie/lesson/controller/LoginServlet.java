package com.yogie.lesson.controller;

import com.yogie.lesson.dao.StudentDao;
import com.yogie.lesson.dao.impl.StudentDaoImpl;
import com.yogie.lesson.domain.Student;
import com.yogie.lesson.util.BeanUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: BaseDemo
 * @Date: 2019/5/24 15:23
 * @Author: Chenyogie
 * @Description:
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet{

    StudentDao dao = new StudentDaoImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Student stu = BeanUtil.getBean(req, Student.class);
        Student stu1 = dao.queryByUsernameAndPassword(stu.getUsername(),stu.getPassword());
        if(stu1!=null){
            //登录成功，将用户名存入session
            req.getSession().setAttribute("NAME_IN_SESSION",stu.getUsername());
            //跳转到系统列表
            resp.sendRedirect("/xx/controller?cmd=query");
        }else{
            //登录失败，返回登录页面
            resp.sendRedirect("/xx/index.html");
        }
    }
}
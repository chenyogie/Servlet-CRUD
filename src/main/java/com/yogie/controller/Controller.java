package com.yogie.controller;

import com.yogie.dao.StudentDao;
import com.yogie.dao.impl.StudentDaoImpl;
import com.yogie.domain.PageBean;
import com.yogie.domain.Student;
import com.yogie.service.PageBeanService;
import com.yogie.util.BeanUtil;
import com.yogie.util.FileUploadUtil;
import com.yogie.util.StringUtil;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @program: BaseDemo
 * @Date: 2019/5/24 15:23
 * @Author: Chenyogie
 * @Description:
 */
@WebServlet("/controller")
public class Controller extends HttpServlet{

    StudentDao dao = new StudentDaoImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求携带的操作参数名[文件上传与下载的时候，此方法获取不到参数名]
        String cmd = req.getParameter("cmd");
        //请从求头中获取请求是以什么方式传递过来的
        String header = req.getHeader("Content-Type");
        Map<String, String[]> upload = null;
        //如果是文件上传的形式传递过来的
        if(header!=null && header.contains("multipart/form-data")){
            //对统一个req对象，FileUploadUtil只能使用一次，第二次的时候req携带的参数全部为空。
            //cmd = "add".equals(FileUploadUtil.getMethod(req))?("add"):("edit");
            //获取文件中所有的字段及文件
            upload = FileUploadUtil.upload(req);
            //遍历这些字段，找到前台设置的标志位
            for (String s : upload.keySet()) {
                if("cmd".equals(s)){
                    cmd = "add".equals(upload.get(s)[0])?"add":"edit";
                }
            }
        }
        //增加
        if ("add".equals(cmd)) {
            add(req, resp,upload);
        //删除
        } else if ("del".equals(cmd)) {
            del(req, resp);
        //修改
        } else if ("edit".equals(cmd)) {
            edit(req, resp,upload);
        } else if ("query".equals(cmd)) {
            query(req, resp);
        //跳转到添加数据的页面
        } else if ("turntoadd".equals(cmd)) {
            //设置操作参数为添加
            req.setAttribute("cmd", "add");
            req.getRequestDispatcher("/jsp/edit.jsp").forward(req, resp);
        //跳转到修改数据的页面
        } else if ("turntoedit".equals(cmd)) {
            String id = req.getParameter("id");
            Student stu = dao.queryOne(new Long(id));
            //设置操作参数为修改
            req.setAttribute("cmd", "edit");
            req.setAttribute("stu", stu);
            req.getRequestDispatcher("/jsp/edit.jsp").forward(req, resp);
        }else if("query".equals(cmd)){
            query(req,resp);
        }else if("login".equals(cmd)){
            login(req,resp);
        }
    }

    /**
     * 向数据库添加一条数据
     * @param req
     * @param resp
     * @param upload
     * @throws IOException
     */
    private void add(HttpServletRequest req, HttpServletResponse resp, Map<String, String[]> map) throws IOException {
        Student stu = new Student();
        try {
            BeanUtils.copyProperties(stu,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //添加
        dao.add(stu);
        resp.sendRedirect("/xx/index.html");
    }

    /**
     * 向数据库删除一条数据
     * @param req
     * @param resp
     * @throws IOException
     */
    private void del(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        dao.delById(Long.valueOf(id));
        resp.sendRedirect("/xx/index.html");
    }

    /**
     * 向数据库修改一条数据
     * @param req
     * @param resp
     * @param upload
     * @throws IOException
     */
    private void edit(HttpServletRequest req, HttpServletResponse resp, Map<String, String[]> map) throws IOException {
        /*Student stu = BeanUtil.getBean(req, Student.class);*/
        Student stu = new Student();
        try {
            //将字段封装到student对象中
            BeanUtils.copyProperties(stu,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //将数据库端还没有更新的那一条数据取出来
        Student studb = dao.queryOne(stu.getId());
        //如果前端进行重新上传图片
        if(!StringUtil.isNull(stu.getHeadimg())){
            //如果原来上传过图片
            if(!StringUtil.isNull(studb.getHeadimg())){
                //将原来服务器端保存的图片删除
                String contextPath = req.getServletContext().getContextPath();
                String path = studb.getHeadimg().substring(contextPath.length());
                String realPath = req.getServletContext().getRealPath(path);
                File file = new File(realPath);
                file.delete();
            }
        }
        //更新
        dao.update(stu);
        resp.sendRedirect("/xx/index.html");
    }

    /**
     * 分页查询+模糊查询
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取当前页面
        String currentPage = req.getParameter("currentPage");
        if(StringUtil.isNull(currentPage)){
            currentPage = "1";
        }
        //获取每页展示的条数
        String pageSize = req.getParameter("pageSize");
        if(StringUtil.isNull(pageSize)){
            pageSize = "5";
        }
        //获取模糊查询的关键字
        String key = req.getParameter("search_key");
        PageBeanService service = new PageBeanService();
        PageBean pageBean = service.queryPageBean(new Integer(currentPage), new Integer(pageSize),key);
        req.setAttribute("pageBean",pageBean);
        req.getRequestDispatcher("/jsp/list.jsp").forward(req,resp);
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Student stu = BeanUtil.getBean(req, Student.class);
        Student stu1 = dao.queryByUsernameAndPassword(stu.getUsername(),stu.getPassword());
        if(stu1!=null){
            //登录成功，将用户名存入session
            req.getSession().setAttribute("username",stu.getUsername());
            //跳转到系统列表
            resp.sendRedirect("/xx/controller?cmd=query");
        }else{
            //登录失败，返回登录页面
            resp.sendRedirect("/xx/index.html");
        }
    }
}
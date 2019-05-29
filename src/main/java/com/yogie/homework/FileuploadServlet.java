package com.yogie.homework;

import com.yogie.lesson.domain.Student;
import com.yogie.lesson.util.FileUploadUtil;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @program: BaseDemo
 * @Date: 2019/5/28 10:53
 * @Author: Chenyogie
 * @Description:
 */
@WebServlet("/upload")
public class FileuploadServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> upload = FileUploadUtil.upload(req);
        Student student = new Student();
        try {
            BeanUtils.copyProperties(student,upload);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(student);
    }
}

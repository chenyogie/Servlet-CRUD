package com.yogie.lesson.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: BaseDemo
 * @Date: 2019/5/27 23:23
 * @Author: Chenyogie
 * @Description:
 */
public class EncodingFilter implements Filter {
    private String charset;
    private Boolean force;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //读取配置文件中filter中的配置
        charset = filterConfig.getInitParameter("charset");
        //如果用户没有配置编码方式
        if(charset==null || "".equals(charset)){
            //设置一个默认的编码
            charset = "UTF-8";
        }
        //Boolean.parseBoolean(null)的结果也为false
        force = Boolean.parseBoolean(filterConfig.getInitParameter("force"));

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse)servletResponse;
        //获取请求对象携带过来的编码方式
        String ce = req.getCharacterEncoding();
        if( ce==null || "".equals(ce) || force){
            //如果request请求中没有设置编码方式，
            //如果配置了强制使用配置中的编码方式
            req.setCharacterEncoding(charset);
        }
        filterChain.doFilter(req,resp);
    }

    @Override
    public void destroy() {

    }
}

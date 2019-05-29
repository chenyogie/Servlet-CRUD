package com.yogie.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @program: BaseDemo
 * @Date: 2019/5/27 11:16
 * @Author: Chenyogie
 * @Description:
 */
public class LoginFilter implements Filter {
    String[] strs = null;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        strs = filterConfig.getInitParameter("outs").split(",");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        //从session中获得key为username的对象
        Object obj = req.getSession().getAttribute("NAME_IN_SESSION");
        //获取到请求路径中的uri描述，并按照反斜杠分割
        String[] split = req.getRequestURI().split("/");
        //将配置中的路径放到list中
        List<String> list = Arrays.asList(strs);
        //如果配置的路径中不包含此次请求资源且session为空，不能放行
        if(!list.contains(split[split.length-1]) && obj==null){
            resp.sendRedirect("/xx/index.html");
            return;
        }
        //放行
        filterChain.doFilter(req,resp);
    }

    @Override
    public void destroy() {

    }
}

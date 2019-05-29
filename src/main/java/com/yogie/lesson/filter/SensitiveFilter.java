package com.yogie.lesson.filter;

import com.yogie.lesson.request.MyHttpRequestWapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @program: BaseDemo
 * @Date: 2019/5/27 16:11
 * @Author: Chenyogie
 * @Description:
 */
@WebFilter("/essay")
public class SensitiveFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //将servletRequest转换成HttpServletRequest对象
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        //HttpServletRequest转换成MyHttpRequestWapper，
        //在这个转换的过程中，请求对象所携带的文本信息中的敏感信息被过滤掉了
        MyHttpRequestWapper requestWapper = new MyHttpRequestWapper(req);
        //放行后，request对象已经是经过包装的request对象了
        filterChain.doFilter(requestWapper, servletResponse);
    }

    @Override
    public void destroy() {

    }
}

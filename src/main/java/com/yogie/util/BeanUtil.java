package com.yogie.util;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: BaseDemo
 * @Date: 2019/5/22 12:33
 * @Author: Chenyogie
 * @Description:
 */
public class BeanUtil {
    /**
     * 将请求对象的数据封装到指定的bean对象中
     * @param req 请求对象
     * @param cla 指定的bean类型
     * @param <T> 泛型
     * @return 返回的bean对象
     */
    public static <T> T getBean(HttpServletRequest req,Class<T> cla){
        T t = null;
        try {
            t = cla.newInstance();
            BeanUtils.copyProperties(t, req.getParameterMap());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }
}

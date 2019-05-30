package com.yogie.util;

/**
 * @program: BaseDemo
 * @Date: 2019/5/30 21:14
 * @Author: Chenyogie
 * @Description:
 */
public class StringUtil {

    /**
     * 判断字符串是否为空
     * @param str
     * @return
     */
    public static boolean isNull(String str){
        return str==null || "".equals(str);
    }
}

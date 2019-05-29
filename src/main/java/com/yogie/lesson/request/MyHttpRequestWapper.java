package com.yogie.lesson.request;

import com.yogie.lesson.util.SensitiveUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @program: BaseDemo
 * @Date: 2019/5/27 15:58
 * @Author: Chenyogie
 * @Description: 包装类做过滤敏感词汇
 */
public class MyHttpRequestWapper extends HttpServletRequestWrapper {
    private HttpServletRequest req = null;
    public MyHttpRequestWapper(HttpServletRequest request) {
        //必须显示调用父类的构造方法，因为父类没有默认的无参构造方法
        super(request);
        //将HttpServletRequest对象赋值给字段
        req = request;
    }

    @Override
    public String getParameter(String name) {
        //根据参数名获取请求对象中的对应的值
        String value = req.getParameter(name);
        return SensitiveUtil.filterWords(value,"*");
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        //获取请求对象中的键值对map
        Map<String, String[]> map = req.getParameterMap();
        //准备一个返回的map
        Map<String, String[]> result = new HashMap<>();
        //取出其中所有的键值对集合
        Set<Map.Entry<String, String[]>> entries = map.entrySet();
        //遍历集合
        for (Map.Entry<String, String[]> entry : entries) {
            //key
            String key = entry.getKey();
            //value：有的字段可能对应多个值，例如select标签
            String[] value = entry.getValue();
            //设计一个与value一致长度的数组
            String[] arr = new String[value.length];
            //遍历这个vlaue数组,将数组中每个元素的字符串都进行敏感词过滤
            for (int i = 0; i < value.length; i++) {
                arr[i] = SensitiveUtil.filterWords(value[i],"*");
            }
            //将过滤后的值保存到map集合中
            result.put(key,arr);
        }
        return result;
    }
}

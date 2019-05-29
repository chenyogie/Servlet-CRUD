package com.yogie.lesson.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @program: BaseDemo
 * @Date: 2019/5/27 16:04
 * @Author: Chenyogie
 * @Description:
 */
public class SensitiveUtil {

    private static List<String> keyWords = new ArrayList<>();

    static{
        Scanner sc = new Scanner(Thread.currentThread().getContextClassLoader().getResourceAsStream("sensitive.txt"),"UTF-8");
        while (sc.hasNextLine()){
            String line = sc.nextLine();
            if(line!=null || !"".equals(line)){
                keyWords.add(line);
            }
        }
        sc.close();
    }


    /**
     * 将str中的敏感词替换为指定字符拼接成的字符串
     * @param str
     * @param mask
     * @return
     */
    public static String filterWords(String str,String mask){
        for (String keyWord : keyWords) {
            if(str!=null && str.indexOf(keyWord) >= 0){
                str = str.replaceAll(keyWord,buildMask(keyWord,mask));
            }
        }
        return str;
    }

    /**
     * 配置keyword长度的替代字符
     * @param keyWord
     * @param mask
     * @return
     */
    private static String buildMask(String keyWord, String mask) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < keyWord.length(); i++) {
            sb.append(mask);
        }
        return sb.toString();
    }
}

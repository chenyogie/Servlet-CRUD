package com.yogie.lesson.util;

import com.yogie.lesson.exception.AutoException;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @program: BaseDemo
 * @Date: 2019/5/28 12:41
 * @Author: Chenyogie
 * @Description:
 */
public class FileUploadUtil {

    static List<String> list = Arrays.asList("jpg","png","bmp");

    /**
     * 文件上传
     * @param req
     * @return
     */
    public static Map<String,String[]> upload(HttpServletRequest req){
        DiskFileItemFactory factory = new DiskFileItemFactory();
         /*//设置缓存目录   缓存一般不用设置，默认的就好
        factory.setRepository(new File("F:/temp"));
        //设置缓存目录大小
        factory.setSizeThreshold(1024*1024*2);*/
        ServletFileUpload upload = new ServletFileUpload(factory);
        Map<String,String[]> map = new HashMap<>();
        try {
            //设置限制单个文件大小
            upload.setFileSizeMax(1024*1024);//30kb
            //设置限制总的文件的大小
            upload.setSizeMax(1024*1024*2);//2M
            //从request对象中获取所有的元素
            List<FileItem> fileItems = upload.parseRequest(req);
            //迭代器迭代request对象中的所有元素
            Iterator<FileItem> iterator = fileItems.iterator();
            while (iterator.hasNext()){
                //得到单个元素
                FileItem item = iterator.next();
                //如果是普通的字段
                if(item.isFormField()){
                    //获取字段的名称
                    String fieldName = item.getFieldName();
                    String value = item.getString("UTF-8");
                    //如果已经有同名的字段(复选框)
                    if(map.containsKey(fieldName)){
                        //以前map集合中的值
                        String[] oldvalue = map.get(fieldName);
                        //构建一个新的数组，长度为旧的数组的长度加1
                        String[] newvalue = new String[oldvalue.length+1];
                        //将旧数组的中的元素全部拷贝到新的数组中
                        System.arraycopy(oldvalue,0,newvalue,0,oldvalue.length);
                        //将读取的value放入数组的最后一个元素
                        newvalue[newvalue.length-1] = value;
                        //将这个新的数组添加到map集合中
                        map.put(fieldName,newvalue);
                    }
                    //一个字段对应一个值
                    if(!map.containsKey(fieldName)){
                        map.put(fieldName,new String[]{value});
                    }
                }else if(item.getSize()==0){//item.getSize()如果为零，表示用户没有上传文件
                    map.put("headimg",null);//将headimg字段设置为null
                }else{//如果上传的是文件
                    //文件的名称：因为浏览器的不同，这个filename有可能包含的是全限定名
                    String filename = item.getName();
                    //获取文件的名称
                    String name = FilenameUtils.getName(filename);
                    //获取到本项目下的img目录的绝对路径
                    String realPath = req.getServletContext().getRealPath("/img");
                    //创建目录
                    File dir = new File(realPath);
                    dir.mkdirs();
                    //解决文件名称重复的问题
                    String uuid = UUIDUtil.getUUID();
                    //获取文件的后缀名
                    String extension = FilenameUtils.getExtension(filename);
                    //如果不是指定的文件后缀名
                    if(!list.contains(extension)){
                        throw new AutoException("不是指定的文件，请选择jpg、png格式的文件！");
                    }
                    String path = req.getServletContext().getContextPath()+"/img/"+uuid+"-"+name;
                    //将文件的路径保存到map集合中，方便beanutil拷贝到实体中进行持久化操作
                    map.put("headimg",new String[]{path});
                    //文件保存的路径
                    File uploadFile = new File(dir,uuid+"-"+name);
                    item.write(uploadFile);
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 获取操作的标志符
     * @param req
     * @return
     */
    public static String getMethod(HttpServletRequest req){
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        String result = null;
        try {
            List<FileItem> fileItems = upload.parseRequest(req);
            //迭代器迭代request对象中的所有元素
            Iterator<FileItem> iterator = fileItems.iterator();
            while (iterator.hasNext()) {
                //得到单个元素
                FileItem item = iterator.next();
                //如果是普通的字段
                if (item.isFormField()) {
                    //获取字段的名称
                    String fieldName = item.getFieldName();
                    //如果是名为cmd的字段
                    if(fieldName.equals("cmd")){
                        result = item.getString("UTF-8");
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
}

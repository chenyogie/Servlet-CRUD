package com.yogie.domain;

/**
 * @program: BaseDemo
 * @Date: 2019/5/28 1:01
 * @Author: Chenyogie
 * @Description:
 */
public class Essay {
    private String id;
    private long stu_id;
    private String title;
    private String content;

    public Essay(){}

    public Essay(String id, long stu_id, String title, String content) {
        this.id = id;
        this.stu_id = stu_id;
        this.title = title;
        this.content = content;
    }

    public Essay(long stu_id, String title, String content) {
        this.stu_id = stu_id;
        this.title = title;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getStu_id() {
        return stu_id;
    }

    public void setStu_id(long stu_id) {
        this.stu_id = stu_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Essay{" +
                "id='" + id + '\'' +
                ", stu_id=" + stu_id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}

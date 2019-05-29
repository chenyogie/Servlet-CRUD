package com.yogie.domain;

import java.math.BigDecimal;

/**
 * @program: BaseDemo
 * @Date: 2019/5/23 14:52
 * @Author: Chenyogie
 * @Description:
 */
public class Student {
    private long id;
    private long grade_id;
    private String username;
    private String password;
    private int age;
    private boolean sex;
    private String intro;
    private BigDecimal account;
    private String headimg;

    public Student() {
    }

    public Student(long id, long grade_id, String username, String password, int age, boolean sex, String intro, BigDecimal account, String headimg) {
        this.id = id;
        this.grade_id = grade_id;
        this.username = username;
        this.password = password;
        this.age = age;
        this.sex = sex;
        this.intro = intro;
        this.account = account;
        this.headimg = headimg;
    }

    public Student(long grade_id, String username, String password, int age, boolean sex, String intro, BigDecimal account, String headimg) {
        this.grade_id = grade_id;
        this.username = username;
        this.password = password;
        this.age = age;
        this.sex = sex;
        this.intro = intro;
        this.account = account;
        this.headimg = headimg;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getGrade_id() {
        return grade_id;
    }

    public void setGrade_id(long grade_id) {
        this.grade_id = grade_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public BigDecimal getAccount() {
        return account;
    }

    public void setAccount(BigDecimal account) {
        this.account = account;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", grade_id=" + grade_id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", intro='" + intro + '\'' +
                ", account=" + account +
                ", headimg='" + headimg + '\'' +
                '}';
    }
}

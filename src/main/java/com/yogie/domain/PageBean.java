package com.yogie.domain;

import java.util.List;

/**
 * @program: BaseDemo
 * @Date: 2019/5/24 16:54
 * @Author: Chenyogie
 * @Description:
 */
public class PageBean {
    private List<Student> data;//当前页的数据
    private Integer firstPage;//首页
    private Integer prePage;//上一页
    private Integer nextPage;//下一页
    private Integer totalPage;//末页、总页数
    private Integer currentPage;//当前页
    private Integer totalCount;//总记录数
    private Integer pageSize;//每页显示的记录数

    public PageBean() {
    }

    public List<Student> getData() {
        return data;
    }

    public void setData(List<Student> data) {
        this.data = data;
    }

    public Integer getFirstPage() {
        return 1;
    }

    public void setFirstPage(Integer firstPage) {
        this.firstPage = firstPage;
    }

    /**
     * 计算上一页
     * @return
     */
    public Integer getPrePage() {
        return getCurrentPage()==getFirstPage()?1:getCurrentPage()-1;
    }

    public void setPrePage(Integer prePage) {
        this.prePage = prePage;
    }

    /**
     * 计算下一页
     * @return
     */
    public Integer getNextPage() {
        return getCurrentPage()==getTotalPage()?getTotalPage():getCurrentPage()+1;
    }

    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }

    /**
     * 根据总记录数，计算页数
     * @return
     */
    public Integer getTotalPage() {
        return getTotalCount()%getPageSize()==0?getTotalCount()/getPageSize():getTotalCount()/getPageSize()+1;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "data=" + data +
                ", firstPage=" + firstPage +
                ", prePage=" + prePage +
                ", nextPage=" + nextPage +
                ", totalPage=" + totalPage +
                ", currentPage=" + currentPage +
                ", totalCount=" + totalCount +
                ", pageSize=" + pageSize +
                '}';
    }
}

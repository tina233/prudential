package com.prudential.car.common;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Preconditions;

import java.util.List;

/**
 * @Author:qutingting
 * @Description: 分页查询结果封装
 */
public class PageRestResult<T> extends RestResult {
    private int pageSize = 20;
    private int total;
    private int currentPageNumber = 1;
    private List<T> items;

    public static PageRestResult transferFromPageInfo(PageInfo pageInfo) {
        Preconditions.checkArgument(pageInfo != null);

        PageRestResult pageData = new PageRestResult();
        pageData.setPageSize(pageInfo.getPageSize());
        pageData.setCurrentPageNumber(pageInfo.getPageNum());
        pageData.setItems(pageInfo.getList());
        pageData.setTotal((int) pageInfo.getTotal());

        return pageData;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCurrentPageNumber() {
        return currentPageNumber;
    }

    public void setCurrentPageNumber(int currentPageNumber) {
        this.currentPageNumber = currentPageNumber;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}

package com.example.result;

import com.example.pagination.PageInfo;
import com.example.utils.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class ResultPage<T> extends Result<List<T>> {

    private static final long serialVersionUID = 1L;

    private PageInfo pageInfo = null;

    public ResultPage() {
        this(new ArrayList<T>());
    }

    public ResultPage(List<T> data) {
        super.setData(data);
    }

    public ResultPage(PageInfo pageInfo) {
        this(new ArrayList<T>());
        this.pageInfo = pageInfo;
    }

    public <V> ResultPage(ResultPage<V> data, Class<T> type) {
        super.setData(BeanUtils.copyListProperties(data.getData(), type));
        this.pageInfo = BeanUtils.copyProperties(data.getPageInfo(), PageInfo.class);
    }

    public ResultPage(List<T> data, PageInfo pageInfo) {
        super.setData(data);
        this.pageInfo = BeanUtils.copyProperties(pageInfo, PageInfo.class);
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = BeanUtils.copyProperties(pageInfo, PageInfo.class);
    }

    public ResultPage<T> addItem(T obj) {
        getData().add(obj);
        return this;
    }
}
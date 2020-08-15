package com.flitsneak.service;

import com.flitsneak.entity.PageResult;
import com.flitsneak.entity.QueryPageBean;
import com.flitsneak.pojo.CheckItem;

import java.util.List;

/**
 * 服务接口
 */
public interface CheckitemService {

    public void add(CheckItem checkItem);

    public PageResult pageQuery(Integer currentPage,Integer pageSize,String queryString);

    public void delete(Integer id);

    public CheckItem findById(Integer id);

    public void edit(CheckItem checkItem);

    public List<CheckItem> findAll();

}

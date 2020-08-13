package com.flitsneak.service;

import com.flitsneak.entity.PageResult;
import com.flitsneak.entity.QueryPageBean;
import com.flitsneak.pojo.CheckItem;

/**
 * 服务接口
 */
public interface CheckitemService {

    public void add(CheckItem checkItem);

    public PageResult pageQuery(Integer currentPage,Integer pageSize,String queryString);

}

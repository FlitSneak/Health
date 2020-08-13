package com.flitsneak.dao;

import com.flitsneak.pojo.CheckItem;
import com.github.pagehelper.Page;

public interface CheckitemDao {
    public void add(CheckItem checkItem);
    public Page<CheckItem> selectByCondition(String queryString);

}

package com.flitsneak.dao;

import com.flitsneak.pojo.CheckItem;
import com.github.pagehelper.Page;

import java.util.List;

public interface CheckitemDao {
    public void add(CheckItem checkItem);
    public Page<CheckItem> selectByCondition(String queryString);
    public long findCountByCheckItemId(Integer checkItemId);
    public void delete(Integer id);
    public CheckItem findById(Integer id);
    public void edit(CheckItem checkItem);
    public List<CheckItem> findAll();

}

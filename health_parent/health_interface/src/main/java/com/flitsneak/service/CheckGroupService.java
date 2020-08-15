package com.flitsneak.service;

import com.flitsneak.entity.PageResult;
import com.flitsneak.entity.QueryPageBean;
import com.flitsneak.pojo.CheckGroup;

import java.util.List;

public interface CheckGroupService {
    public void add(CheckGroup checkGroup,Integer[] checkIds);
    public PageResult findPage(QueryPageBean queryPageBean);
    public CheckGroup findById(Integer id);
    public Integer[] findByCheckGroupId(Integer id);
    public void edit(CheckGroup checkGroup, Integer[] ids);
    public void delete(Integer id);
}

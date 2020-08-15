package com.flitsneak.dao;

import com.flitsneak.pojo.CheckGroup;
import com.github.pagehelper.Page;

import java.util.Map;

public interface CheckGroupDao {
    public void add(CheckGroup checkGroup);
    public void setCheckGroupAndCheckItem(Map map);
    public Page<CheckGroup> selectByConditon(String string);
    public CheckGroup findById(Integer id);
    public Integer[] findByCheckGroupId(Integer id);
    public void editCheckGroup(CheckGroup checkGroup);
    public void deletAssociation(Integer id);
    public void deleteCheckGroup(Integer id);
}

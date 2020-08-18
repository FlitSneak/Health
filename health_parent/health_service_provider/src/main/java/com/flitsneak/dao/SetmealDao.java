package com.flitsneak.dao;

import com.flitsneak.pojo.CheckGroup;
import com.flitsneak.pojo.Setmeal;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface SetmealDao {
    public Page<Setmeal> findPage(String string);
    public List<CheckGroup> findAll();
    public void add(Setmeal setmeal);
    public void addCgAndSm(Map<String,Integer> map);
}

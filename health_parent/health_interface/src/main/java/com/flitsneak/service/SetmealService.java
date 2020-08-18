package com.flitsneak.service;

import com.flitsneak.entity.PageResult;
import com.flitsneak.pojo.CheckGroup;
import com.flitsneak.pojo.Setmeal;

import java.util.List;

public interface SetmealService {
    public PageResult findPage(Integer cp,Integer ps,String qs);
    public List<CheckGroup> findAll();
    public void add(Setmeal setmeal,Integer[] ids);
}

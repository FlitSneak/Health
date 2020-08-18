package com.flitsneak.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.flitsneak.constant.RedisConstant;
import com.flitsneak.dao.SetmealDao;
import com.flitsneak.entity.PageResult;
import com.flitsneak.pojo.CheckGroup;
import com.flitsneak.pojo.Setmeal;
import com.flitsneak.service.SetmealService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = SetmealService.class)
@Transactional
public class SetmealServiceImpl implements SetmealService{
    @Autowired
    private JedisPool jedisPool;

    @Autowired
    private SetmealDao setmealDao;
    public PageResult findPage(Integer cp, Integer ps, String qs) {
        PageHelper.startPage(cp,ps);
        Page<Setmeal> page = setmealDao.findPage(qs);
        PageResult pageResult = new PageResult(page.getTotal(),page);
        return pageResult;
    }

    public List<CheckGroup> findAll() {
        return setmealDao.findAll();
    }

    public void add(Setmeal setmeal, Integer[] ids) {
        setmealDao.add(setmeal);
        if(ids !=null && ids.length>0){
            setCGAndSm(setmeal.getId(),ids);
        }
        //redis缓存
        jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES,setmeal.getImg());
    }

    public void setCGAndSm(Integer id, Integer[] ids){

        for (Integer integer : ids) {
            Map<String,Integer> map = new HashMap<>();
            map.put("setMealId",id);
            map.put("checkGroupId",integer);
            setmealDao.addCgAndSm(map);
        }
    }


}

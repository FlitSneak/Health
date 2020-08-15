package com.flitsneak.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.flitsneak.dao.CheckitemDao;
import com.flitsneak.entity.PageResult;
import com.flitsneak.pojo.CheckItem;
import com.flitsneak.service.CheckitemService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 检查项服务
 */
@Service(interfaceClass = CheckitemService.class )
@Transactional
public class CheckitemServiceImpl implements CheckitemService {
    @Autowired
    private CheckitemDao checkitemDao;

    public void add(CheckItem checkItem) {
        checkitemDao.add(checkItem);

    }

    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage,pageSize);
        Page<CheckItem> page = checkitemDao.selectByCondition(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }

    public void delete(Integer id)throws RuntimeException{
        long count = checkitemDao.findCountByCheckItemId(id);
        if (count>0) {
            throw new RuntimeException("当前检查项被引用不可删除");
        }
        checkitemDao.delete(id);
    }


    public CheckItem findById(Integer id) {
        CheckItem checkItem = checkitemDao.findById(id);
        return checkItem;
    }


    public void edit(CheckItem checkItem) {
        checkitemDao.edit(checkItem);

    }

    public List<CheckItem> findAll(){
        return checkitemDao.findAll();
    }


}

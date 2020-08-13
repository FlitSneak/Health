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
}

package com.flitsneak.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.flitsneak.dao.CheckGroupDao;
import com.flitsneak.entity.PageResult;
import com.flitsneak.entity.QueryPageBean;
import com.flitsneak.pojo.CheckGroup;
import com.flitsneak.service.CheckGroupService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = CheckGroupService.class)
@Transactional
public class CheckGroupServiceImpl implements CheckGroupService{

    @Autowired
    private CheckGroupDao checkGroupDao;

    public void add(CheckGroup checkGroup,Integer[] checkIds){
        checkGroupDao.add(checkGroup);
        //设置检查组和检查项的多对多的关联关系，操作t_checkgroup_checkitem表
        Integer checkGroupId = checkGroup.getId();
        this.setCheckGroupAndCheckItem(checkGroupId,checkIds);

    }

    public PageResult findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        Page<CheckGroup> page = checkGroupDao.selectByConditon(queryPageBean.getQueryString());
        return new PageResult(page.getTotal(),page.getResult());


    }

    public CheckGroup findById(Integer id) {

        return checkGroupDao.findById(id);
    }

    public Integer[] findByCheckGroupId(Integer id){
        return checkGroupDao.findByCheckGroupId(id);
    }

    public void edit(CheckGroup checkGroup,Integer[] ids){
        checkGroupDao.editCheckGroup(checkGroup);
        //没有办法去更新关联关系,因此需要先删除关联关系。
        checkGroupDao.deletAssociation(checkGroup.getId());
        //重新建立当前检查组和检查项的关联关系
        Integer checkGroupId = checkGroup.getId();
        this.setCheckGroupAndCheckItem(checkGroupId,ids);
    }

    public void delete(Integer id){
        checkGroupDao.deleteCheckGroup(id);
        checkGroupDao.deletAssociation(id);

    }

    //建立检查组和检查项多对多关系
    public void setCheckGroupAndCheckItem(Integer checkGroupId,Integer[] checkitemIds){
        if(checkitemIds != null && checkitemIds.length > 0){
            for (Integer checkitemId : checkitemIds) {
                Map<String,Integer> map = new HashMap<>();
                map.put("checkgroupId",checkGroupId);
                map.put("checkitemId",checkitemId);
                checkGroupDao.setCheckGroupAndCheckItem(map);
            }
        }
    }
}

package com.flitsneak.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.flitsneak.constant.MessageConstant;
import com.flitsneak.entity.PageResult;
import com.flitsneak.entity.QueryPageBean;
import com.flitsneak.entity.Result;
import com.flitsneak.pojo.CheckGroup;
import com.flitsneak.service.CheckGroupService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/checkgroup")
public class CheckgroupController {

    @Reference
    private CheckGroupService checkGroupService;

    @PostMapping("/add")
    public Result add(@RequestBody CheckGroup checkGroup,Integer[] checkIds){
        try {
            checkGroupService.add(checkGroup,checkIds);
            return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);
        } catch (Exception e) {
            return new Result(false,MessageConstant.DELETE_CHECKGROUP_FAIL);
        }
    }

    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
        try {
            PageResult pageResult = checkGroupService.findPage(queryPageBean);
            return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,pageResult);
        } catch (Exception e) {
            return new Result(false,MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }

    @GetMapping("/findById")
    public Result findById (Integer id){
        try {
            CheckGroup checkGroup = checkGroupService.findById(id);
            return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkGroup);
        } catch (Exception e) {
            return new Result(false,MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }

    @GetMapping("/findByCheckGroupId")
    public Result findByCheckGroupId(Integer id){
        try {
            Integer[] ids = checkGroupService.findByCheckGroupId(id);
            return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,ids);
        } catch (Exception e) {
            return new Result(false,MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }

    @PostMapping("/edit")
    public Result edit(@RequestBody CheckGroup checkGroup,Integer[] ids){
        try {
            checkGroupService.edit(checkGroup,ids);
            return new Result(true,MessageConstant.EDIT_CHECKGROUP_SUCCESS);
        } catch (Exception e) {
            return new Result(false,MessageConstant.EDIT_CHECKGROUP_FAIL);
        }

    }

    @GetMapping("/delete")
    public Result delete(Integer id){
        try {
            checkGroupService.delete(id);
            return new Result(true,MessageConstant.DELETE_CHECKGROUP_SUCCESS);
        } catch (Exception e) {
            return new Result(false,MessageConstant.DELETE_CHECKGROUP_FAIL);
        }
    }
}

package com.flitsneak.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.flitsneak.constant.MessageConstant;
import com.flitsneak.entity.PageResult;
import com.flitsneak.entity.QueryPageBean;
import com.flitsneak.entity.Result;
import com.flitsneak.pojo.CheckItem;
import com.flitsneak.service.CheckitemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 检查项管理
 */
@RestController
@RequestMapping("/checkitem")
public class CheckitemController {

    @Reference
    private CheckitemService checkitemService;
    //新增检查项
    @PostMapping("/add")
    public Result add(@RequestBody CheckItem checkItem){

        try {
            checkitemService.add(checkItem);
        } catch (Exception e) {
            e.printStackTrace();
            //服务调用失败
            return new Result(false, MessageConstant.ADD_CHECKITEM_FAIL);
        }
        return new Result(true,MessageConstant.ADD_CHECKITEM_SUCCESS);
    }
    @PostMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = checkitemService.pageQuery(
                queryPageBean.getCurrentPage(),
                queryPageBean.getPageSize(),
                queryPageBean.getQueryString()
        );
        return pageResult;

    }
    @GetMapping("/delete")
    public Result delete(Integer id){
        try {
            checkitemService.delete(id);
        } catch (RuntimeException e) {
            return new Result(false,e.getMessage());
        }catch (Exception e){
            return new Result(false,MessageConstant.DELETE_CHECKITEM_FAIL);
        }
        return new Result(true,MessageConstant.DELETE_CHECKITEM_SUCCESS);
    }
    @GetMapping("/findById")
    public Result findById(Integer id){
        try {
            CheckItem checkItem = checkitemService.findById(id);
            return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItem);
        } catch (Exception e) {
           return new Result(false,MessageConstant.QUERY_CHECKITEM_FAIL);
        }

    }
    @PostMapping("/edit")
    public Result edit(@RequestBody CheckItem checkItem){
        try {
            checkitemService.edit(checkItem);
            return new Result(true,MessageConstant.EDIT_CHECKITEM_SUCCESS);
        } catch (Exception e) {
            return new Result(false,MessageConstant.EDIT_CHECKITEM_FAIL);
        }

    }
    @GetMapping("/findAll")
    public Result findAll(){
        List<CheckItem> list = checkitemService.findAll();

        if(list !=null && list.size()>0){
            return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,list);
        }
        return new Result(false,MessageConstant.QUERY_CHECKITEM_FAIL);
    }

}

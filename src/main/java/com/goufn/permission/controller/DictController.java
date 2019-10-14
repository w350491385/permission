package com.goufn.permission.controller;

import com.goufn.permission.annotation.Log;
import com.goufn.permission.common.page.PageRequest;
import com.goufn.permission.common.result.Result;
import com.goufn.permission.model.SysDict;
import com.goufn.permission.service.DictService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("dict")
public class DictController {

    @Autowired
    private DictService dictService;

    @Log("新增字典")
    @RequiresPermissions({"sys:dict:add", "sys:dict:edit"})
    @PostMapping(value="/save")
    public Result save(@RequestBody SysDict record) {
        return Result.success(dictService.saveOrUpdate(record));
    }

    @Log("删除字典")
    @RequiresPermissions("sys:dict:delete")
    @PostMapping(value="/delete")
    public Result delete(@RequestBody List<SysDict> records) {
        dictService.delete(records);
        return Result.success(1);
    }

    @RequiresPermissions("sys:dict:view")
    @PostMapping(value="/findPage")
    public Result findPage(@RequestBody PageRequest pageRequest) {
        return Result.success(dictService.findPage(pageRequest));
    }

    @RequiresPermissions("sys:dict:view")
    @GetMapping(value="/findByLable")
    public Result findByLable(@RequestParam String lable) {
        return Result.success(dictService.findByLable(lable));
    }
}

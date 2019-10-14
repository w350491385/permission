package com.goufn.permission.controller;

import com.goufn.permission.annotation.Log;
import com.goufn.permission.common.result.Result;
import com.goufn.permission.model.SysDept;
import com.goufn.permission.service.DeptService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/dept")
public class DeptController {
    @Autowired
    private DeptService deptService;

    @Log("新增/修改部门")
    @RequiresPermissions({"sys:dept:add", "sys:dept:edit"})
    @PostMapping(value="/save")
    public Result save(@RequestBody SysDept record) {
        if (record == null) {
            return Result.error("表格不能为空");
        }
        deptService.saveOrUpdate(record);
        return Result.success();
    }

    @Log("删除部门")
    @RequiresPermissions("sys:dept:delete")
    @PostMapping(value="/delete")
    public Result delete(@RequestBody List<SysDept> records) {
        return Result.success(deptService.delete(records));
    }

    @RequiresPermissions("sys:dept:view")
    @GetMapping(value="/findTree")
    public Result findTree(@RequestParam(required = false) String name) {
        return Result.success(deptService.findTree(name));
    }

}

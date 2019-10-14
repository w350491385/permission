package com.bn.admin.admin.controller;

import com.bn.admin.admin.annotation.Log;
import com.bn.admin.result.Result;
import com.bn.admin.model.SysMenu;
import com.bn.admin.service.MenuService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @Log("新增/修改菜单")
    @PostMapping(value="/save")
    @RequiresPermissions({"sys:menu:add", "sys:menu:edit"})
    public Result save(@RequestBody SysMenu record) {
        if (record.getParentId() == null) {
            record.setParentId(0L);
        }
        menuService.saveOrUpdate(record);
        return Result.success();
    }

    @Log("删除菜单/按钮")
    @PostMapping(value="/delete")
    @RequiresPermissions("sys:menu:delete")
    public Result delete(@RequestBody List<SysMenu> records) {
        for (SysMenu record : records) {
            menuService.removeById(record.getId());
        }
        return Result.success(records.size());
    }

    @GetMapping("/findNavTree")
    @RequiresPermissions("sys:menu:view")
    public Result findNavTree(@RequestParam String username) {
        List<SysMenu> menuList = menuService.findTree(username, 1, null);
        return Result.success(menuList);
    }

    @GetMapping("/findMenuTree")
    @RequiresPermissions("sys:menu:view")
    public Result findMenuTree(@RequestParam String name) {
        List<SysMenu> menuList = menuService.findTree(null, 0, name);
        return Result.success(menuList);
    }

}

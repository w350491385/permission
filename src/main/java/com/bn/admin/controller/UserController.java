package com.bn.admin.controller;

import com.bn.admin.annotation.Log;
import com.bn.admin.model.SysUser;
import com.bn.admin.common.page.PageRequest;
import com.bn.admin.common.result.Result;
import com.bn.admin.service.UserService;
import com.bn.admin.utils.PasswordUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @Log("新增/修改用户")
    @RequiresPermissions({"sys:user:add", "sys:user:edit"})
    @PostMapping(value="/save")
    public Result save(@RequestBody SysUser record) {
        SysUser user = userService.findById(record.getId());
        if(user != null) {
            if("admin".equalsIgnoreCase(user.getName())) {
                return Result.error("超级管理员不允许修改!");
            }
        }
        if(record.getPassword() != null) {
            if(user == null) {
                // 新增用户
                if(userService.findByName(record.getName()) != null) {
                    return Result.error("用户名已存在!");
                }
                PasswordUtil.encryptPassword(record);
            } else {
                // 修改用户, 且修改了密码
                if(!record.getPassword().equals(user.getPassword())) {
                    PasswordUtil.encryptPassword(record);
                }
            }
        }
        return Result.success(userService.save(record));
    }

    @Log("删除用户")
    @RequiresPermissions("sys:user:delete")
    @PostMapping(value="/delete")
    public Result delete(@RequestBody List<SysUser> records) {
        for(SysUser record : records) {
            SysUser sysUser = userService.findById(record.getId());
            if(sysUser != null && "admin".equalsIgnoreCase(sysUser.getName())) {
                return Result.error("超级管理员不允许删除!");
            }
        }
        return Result.success(userService.delete(records));
    }

    @RequiresPermissions("sys:user:view")
    @GetMapping(value="/findByName")
    public Result findByUserName(@RequestParam String name) {
        return Result.success(userService.findByName(name));
    }

    @RequiresPermissions("sys:user:view")
    @GetMapping(value="/findPermissions")
    public Result findPermissions(@RequestParam String name) {
        return Result.success(userService.findPermissions(name));
    }

    @RequiresPermissions("sys:user:view")
    @GetMapping(value="/findUserRoles")
    public Result findUserRoles(@RequestParam Long userId) {
        return Result.success(userService.findUserRoles(userId));
    }

    @Log("查看用户")
    @RequiresPermissions("sys:user:view")
    @PostMapping(value="/findPage")
    public Result findPage(@RequestBody PageRequest pageRequest) {
        return Result.success(userService.findPage(pageRequest));
    }
}

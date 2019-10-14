package com.bn.admin.controller;

import com.bn.admin.jwt.JWTUtil;
import com.bn.admin.model.SysRole;
import com.bn.admin.model.SysRoleMenu;
import com.bn.admin.annotation.Log;
import com.bn.admin.common.page.PageRequest;
import com.bn.admin.common.result.Result;
import com.bn.admin.mapper.RoleMapper;
import com.bn.admin.service.RoleService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleMapper roleMapper;

    @Log("新增/修改角色")
    @RequiresPermissions({"sys:role:add", "sys:role:edit"})
    @PostMapping(value="/save")
    public Result save(@RequestBody SysRole record) {
        String token = (String) SecurityUtils.getSubject().getPrincipal();
        String username = JWTUtil.getUsername(token);
        record.setLastUpdateTime(new Date());
        record.setLastUpdateBy(username);
        SysRole role = roleService.getById(record.getId());
        if(role != null) {
            if("admin".equalsIgnoreCase(role.getName())) {
                return Result.error("超级管理员不允许修改!");
            }
        }
        // 新增角色
        if((record.getId() == null || record.getId() ==0)) {
            if (!roleService.findByName(record.getName()).isEmpty()) {
                return Result.error("角色名已存在!");
            }
            record.setCreateTime(new Date());
            record.setCreateBy(username);
            roleService.save(record);
        } else {
            record.setLastUpdateTime(new Date());
            roleService.updateById(record);
        }
        return Result.success();
    }

    @Log("删除角色")
    @RequiresPermissions("sys:role:delete")
    @PostMapping(value="/delete")
    public Result delete(@RequestBody List<SysRole> records) {
        roleService.delete(records);
        return Result.success();
    }

    @RequiresPermissions("sys:role:view")
    @PostMapping(value="/findPage")
    public Result findPage(@RequestBody PageRequest pageRequest) {
        return Result.success(roleService.findPage(pageRequest));
    }

    @RequiresPermissions("sys:role:view")
    @GetMapping(value="/findAll")
    public Result findAll() {
        return Result.success(roleService.list());
    }

    @RequiresPermissions("sys:role:view")
    @GetMapping(value="/findRoleMenus")
    public Result findRoleMenus(@RequestParam Long roleId) {
        return Result.success(roleService.findRoleMenus(roleId));
    }

    @Log("修改角色权限")
    @RequiresPermissions("sys:role:edit")
    @PostMapping(value="/saveRoleMenus")
    public Result saveRoleMenus(@RequestBody List<SysRoleMenu> records) {
        for(SysRoleMenu record:records) {
            SysRole sysRole = roleMapper.selectById(record.getRoleId());
            if("admin".equalsIgnoreCase(sysRole.getName())) {
                // 如果是超级管理员，不允许修改
                return Result.error("超级管理员拥有所有菜单权限，不允许修改！");
            }
        }
        roleService.saveRoleMenus(records);
        return Result.success();
    }
}

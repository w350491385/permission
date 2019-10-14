package com.bn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bn.page.PageRequest;
import com.bn.page.PageResult;
import com.bn.model.SysMenu;
import com.bn.model.SysRole;
import com.bn.model.SysRoleMenu;

import java.util.List;
import java.util.Set;

public interface RoleService extends IService<SysRole> {

    Set<String> findRoleByUserId(long userId);

    void delete(List<SysRole> roles);

    PageResult findPage(PageRequest pageRequest);

    List<SysMenu> findRoleMenus(long roleId);

    void saveRoleMenus(List<SysRoleMenu> sysRoleMenus);

    List<SysRole> findByName(String name);

}

package com.bn.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bn.admin.model.SysMenu;
import com.bn.admin.model.SysRole;
import com.bn.admin.model.SysRoleMenu;
import com.bn.admin.common.page.PageRequest;
import com.bn.admin.common.page.PageResult;

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

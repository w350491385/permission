package com.bn.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bn.admin.model.SysMenu;
import com.bn.admin.model.SysRole;
import com.bn.admin.model.SysRoleMenu;
import com.bn.admin.common.page.ColumnFilter;
import com.bn.admin.common.page.PageRequest;
import com.bn.admin.common.page.PageResult;
import com.bn.admin.mapper.MenuMapper;
import com.bn.admin.mapper.RoleMenuMapper;
import com.bn.admin.mapper.RoleMapper;
import com.bn.admin.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, SysRole> implements RoleService {
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private RoleMenuMapper roleMenuMapper;
    @Override
    public Set<String> findRoleByUserId(long userId) {
        return baseMapper.findRoleByUserId(userId);
    }


    @Override
    public void delete(List<SysRole> roles) {
        for (SysRole role : roles) {
            removeById(role.getId());
        }
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        ColumnFilter columnFilter = pageRequest.getColumnFilters().get("name");
        LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<>();
        if (columnFilter != null && !StringUtils.isEmpty(columnFilter.getValue())) {
            wrapper.eq(SysRole::getName, columnFilter.getValue());
        }
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        Page<SysRole> page = new Page<>(pageNum, pageSize);
        IPage<SysRole> result = baseMapper.selectPage(page, wrapper);
        PageResult pageResult = new PageResult(result);
        return pageResult;
    }

    @Override
    public List<SysMenu> findRoleMenus(long roleId) {
        SysRole sysRole = baseMapper.selectById(roleId);
        if (sysRole.getName() == "admin") {
            return menuMapper.selectList(null);
        }
        return menuMapper.findByRoleId(roleId);
    }

    @Override
    public void saveRoleMenus(List<SysRoleMenu> records) {
        if(records == null || records.isEmpty()) {
            return ;
        }
        Long roleId = records.get(0).getRoleId();
        roleMenuMapper.delete(new LambdaQueryWrapper<SysRoleMenu>().eq(SysRoleMenu::getRoleId, roleId));
        for(SysRoleMenu record:records) {
            roleMenuMapper.insert(record);
        }
    }

    @Override
    public List<SysRole> findByName(String name) {
        return baseMapper.selectList(new LambdaQueryWrapper<SysRole>().eq(SysRole::getName, name));
    }

}

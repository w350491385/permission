package com.bn.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.bn.page.PageRequest;
import com.bn.page.PageResult;
import com.bn.model.SysUser;
import com.bn.model.SysUserRole;

import java.util.List;
import java.util.Set;

public interface UserService extends IService<SysUser> {

    PageResult findPage(PageRequest pageRequest);

    int delete(List<SysUser> users);

    /**
     * 获取指定用户ID对应的用户账户信息
     * @param userId 用户ID
     * @return 返回用户账户信息
     */
    SysUser findById(Long userId);

    /**
     * 获取指定 userName 对应的用户账户信息
     * @param userName 用户名
     * @return 返回用户账户信息
     */
    SysUser findByName(String userName);


    void updateLoginTime(SysUser user);

    /**
     * 添加一条用户账户信息
     * @param user 需要添加的用户账户信息
     */
    void createUser(SysUser user);

    /**
     * 查找用户的菜单权限标识集合
     * @param userName
     * @return
     */
    Set<String> findPermissions(String userName);

    /**
     * 查找用户的角色集合
     * @param userId
     * @return
     */
    List<SysUserRole> findUserRoles(Long userId);



}

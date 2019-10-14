package com.bn.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bn.admin.model.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MenuMapper extends BaseMapper<SysMenu> {

    /**
     * 根据用户id查询资源集合
     *
     * @param roleId
     * @return set
     */
    List<SysMenu> findByRoleId(Long roleId);

    /**
     * 查询用户按钮
     * @param username
     * @return
     */
    List<SysMenu> findByUserName(String username);
}

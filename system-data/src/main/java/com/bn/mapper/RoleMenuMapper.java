package com.bn.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bn.model.SysRoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface RoleMenuMapper extends BaseMapper<SysRoleMenu> {
}

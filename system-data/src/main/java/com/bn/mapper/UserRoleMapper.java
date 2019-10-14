package com.bn.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bn.model.SysUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserRoleMapper extends BaseMapper<SysUserRole> {
}

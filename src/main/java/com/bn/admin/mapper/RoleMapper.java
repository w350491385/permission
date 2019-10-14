package com.bn.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bn.admin.model.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
@Mapper
public interface RoleMapper extends BaseMapper<SysRole> {

    Set<String> findRoleByUserId(long userId);

}

package com.bn.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bn.admin.model.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper extends BaseMapper<SysUser> {
}

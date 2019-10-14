package com.bn.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bn.admin.page.PageRequest;
import com.bn.admin.page.PageResult;
import com.bn.admin.model.SysDept;

import java.util.List;

public interface DeptService extends IService<SysDept> {

    List<SysDept> findTree(String name);

    PageResult findPage(PageRequest pageRequest);

    int delete(List<SysDept> records);
}

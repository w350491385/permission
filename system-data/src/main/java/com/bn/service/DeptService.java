package com.bn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bn.page.PageRequest;
import com.bn.page.PageResult;
import com.bn.model.SysDept;

import java.util.List;

public interface DeptService extends IService<SysDept> {

    List<SysDept> findTree(String name);

    PageResult findPage(PageRequest pageRequest);

    int delete(List<SysDept> records);
}

package com.bn.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bn.admin.page.PageRequest;
import com.bn.admin.page.PageResult;
import com.bn.admin.model.SysDict;

import java.util.List;

public interface DictService extends IService<SysDict> {

    void delete(List<SysDict> records);

    PageResult findPage(PageRequest pageRequest);

    List<SysDict> findByLable(String lable);
}

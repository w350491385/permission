package com.bn.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bn.admin.model.SysDict;
import com.bn.admin.common.page.PageRequest;
import com.bn.admin.common.page.PageResult;

import java.util.List;

public interface DictService extends IService<SysDict> {

    void delete(List<SysDict> records);

    PageResult findPage(PageRequest pageRequest);

    List<SysDict> findByLable(String lable);
}

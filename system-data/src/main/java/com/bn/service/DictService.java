package com.bn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bn.page.PageRequest;
import com.bn.page.PageResult;
import com.bn.model.SysDict;

import java.util.List;

public interface DictService extends IService<SysDict> {

    void delete(List<SysDict> records);

    PageResult findPage(PageRequest pageRequest);

    List<SysDict> findByLable(String lable);
}

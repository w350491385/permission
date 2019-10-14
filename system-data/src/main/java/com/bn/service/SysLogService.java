package com.bn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bn.page.PageRequest;
import com.bn.page.PageResult;
import com.bn.model.SysLog;

public interface SysLogService extends IService<SysLog> {

    PageResult findPage(PageRequest pageRequest);
}

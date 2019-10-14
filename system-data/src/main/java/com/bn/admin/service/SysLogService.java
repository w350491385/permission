package com.bn.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bn.admin.page.PageRequest;
import com.bn.admin.page.PageResult;
import com.bn.admin.model.SysLog;

public interface SysLogService extends IService<SysLog> {

    PageResult findPage(PageRequest pageRequest);
}

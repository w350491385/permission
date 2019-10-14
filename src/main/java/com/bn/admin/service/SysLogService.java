package com.bn.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bn.admin.model.SysLog;
import com.bn.admin.common.page.PageRequest;
import com.bn.admin.common.page.PageResult;

public interface SysLogService extends IService<SysLog> {

    PageResult findPage(PageRequest pageRequest);
}

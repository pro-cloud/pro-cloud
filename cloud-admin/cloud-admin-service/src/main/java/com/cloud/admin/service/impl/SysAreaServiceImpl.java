package com.cloud.admin.service.impl;

import com.cloud.admin.beans.po.SysArea;
import com.cloud.admin.mapper.SysAreaMapper;
import com.cloud.admin.service.SysAreaService;
import com.cloud.common.data.base.TreeService;
import org.springframework.stereotype.Service;

/**
 * 区域表
 *
 * @author Aijm
 * @date 2019-08-25 21:54:16
 */
@Service
public class SysAreaServiceImpl extends TreeService<SysAreaMapper, SysArea> implements SysAreaService {

}

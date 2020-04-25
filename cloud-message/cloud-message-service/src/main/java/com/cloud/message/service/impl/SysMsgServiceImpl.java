package com.cloud.message.service.impl;

import com.cloud.common.data.base.BaseService;
import com.cloud.message.beans.po.SysMsg;
import com.cloud.message.mapper.SysMsgMapper;
import com.cloud.message.service.SysMsgService;
import org.springframework.stereotype.Service;

/**
 * 短信发送消息日志
 * @author Aijm
 * @since 2020/4/25
 */
@Service
public class SysMsgServiceImpl extends BaseService<SysMsgMapper, SysMsg> implements SysMsgService {

}

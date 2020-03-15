package com.cloud.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.common.data.base.BaseService;
import com.cloud.order.beans.po.OrderTb;
import com.cloud.order.mapper.OrderTbMapper;
import com.cloud.order.service.OrderTbService;
import org.springframework.stereotype.Service;

/**
 *
 *
 * @author Aijm
 * @date 2020-03-15 18:34:59
 */
@Service
public class OrderTbServiceImpl extends ServiceImpl<OrderTbMapper, OrderTb> implements OrderTbService {

}

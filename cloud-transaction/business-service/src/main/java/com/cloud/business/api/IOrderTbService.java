package com.cloud.business.api;


import com.cloud.business.beans.po.OrderTb;
import com.cloud.common.data.base.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 *
 *
 * @author Aijm
 * @date 2020-03-15 18:34:59
 */
@FeignClient(value = "order-service")
public interface IOrderTbService {


    /**
     * 保存订单
     * @param orderTb
     * @return
     */
    @RequestMapping(value="/ordertb", method= RequestMethod.POST)
    public Result save(@RequestBody @Valid OrderTb orderTb);
}

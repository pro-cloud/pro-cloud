package com.cloud.order.controller;

import com.cloud.common.data.base.Result;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.order.api.IAccountTbService;
import com.cloud.order.beans.po.AccountTb;
import com.cloud.order.beans.po.OrderTb;
import com.cloud.order.service.OrderTbService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
 *
 *
 * @author Aijm
 * @date 2020-03-15 18:34:59
 */
@RestController
@RequestMapping("/ordertb" )
@Api(value = "ordertb", tags = "ordertb管理")
public class OrderTbController {

    @Autowired
    private OrderTbService orderTbService;

    @Autowired
    private IAccountTbService accountTbService;


    /**
     * 新增
     * @param orderTb
     * @return Result
     */
    @PostMapping
    public Result save(@RequestBody @Valid OrderTb orderTb) {

        orderTb.setCreateBy(1L);
        orderTb.setCommodityCode("1");
        orderTb.setUserId("1");
        orderTb.setCount(1);
        orderTb.setUpdateBy(1L);
        orderTbService.save(orderTb);

        AccountTb accountTb = new AccountTb();
        accountTb.setId(1L);
        accountTb.setMoney(100);
        accountTbService.updateById(accountTb);

        return Result.success("成功");
    }



}

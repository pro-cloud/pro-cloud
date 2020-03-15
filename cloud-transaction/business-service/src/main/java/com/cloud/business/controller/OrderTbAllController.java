package com.cloud.business.controller;

import com.cloud.business.api.IOrderTbService;
import com.cloud.business.api.IStorageTbService;
import com.cloud.business.beans.po.OrderTb;
import com.cloud.business.beans.po.StorageTb;
import com.cloud.common.data.base.Result;

import io.seata.spring.annotation.GlobalTransactional;
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
@RequestMapping("/ordertbAll" )
@Api(value = "ordertbAll", tags = "ordertbAll管理")
public class OrderTbAllController {

    @Autowired
    private IOrderTbService orderTbService;
    @Autowired
    private IStorageTbService storageTbService;



    /**
     * 新增
     * @param orderTb
     * @return Result
     */
    @PostMapping
    @GlobalTransactional
    public Result save() {
        OrderTb orderTb = new OrderTb();
//        Result save = orderTbService.save(orderTb);
        StorageTb storageTb = new StorageTb();
        storageTb.setId(1L);
        storageTb.setCount(1);
        Result result = storageTbService.updateById(storageTb);
        int i = 1/0;
        return Result.success("操作成功");
    }


}

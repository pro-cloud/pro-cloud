package com.cloud.storage.controller;

import com.cloud.common.data.base.Result;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.storage.beans.po.StorageTb;
import com.cloud.storage.service.StorageTbService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
 *
 *
 * @author Aijm
 * @date 2020-03-15 18:17:15
 */
@RestController
@RequestMapping("/storagetb" )
@Api(value = "storagetb", tags = "storagetb管理")
public class StorageTbController {

    @Autowired
    private StorageTbService storageTbService;


    /**
     * 修改
     * @param storageTb
     * @return Result
     */
    @PutMapping
    public Result updateById(@RequestBody @Valid StorageTb storageTb) {
        StorageTb byId = storageTbService.getById(storageTb.getId());
        byId.setCount(byId.getCount()- storageTb.getCount());
        return Result.success(storageTbService.updateById(byId));
    }


}

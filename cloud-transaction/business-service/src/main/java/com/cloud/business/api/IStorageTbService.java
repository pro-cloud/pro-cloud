package com.cloud.business.api;


import com.cloud.business.beans.po.StorageTb;
import com.cloud.common.data.base.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 *
 *
 * @author Aijm
 * @date 2020-03-15 18:17:15
 */
@FeignClient(value = "storage-service")
public interface IStorageTbService  {

    /**
     * 修改
     * @param storageTb
     * @return Result
     */
    @RequestMapping(value="/storagetb", method= RequestMethod.PUT)
    public Result updateById(@RequestBody @Valid StorageTb storageTb);

}

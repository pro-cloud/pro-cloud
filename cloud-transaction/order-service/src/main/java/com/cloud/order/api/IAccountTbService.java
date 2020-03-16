package com.cloud.order.api;

import com.cloud.common.data.base.Result;
import com.cloud.order.beans.po.AccountTb;
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
 * @date 2020-03-15 18:16:02
 */
@FeignClient(value = "account-service")
public interface IAccountTbService {


    /**
     * 账户扣钱
     * @param accountTb
     * @return
     */
    @RequestMapping(value="/accounttb", method= RequestMethod.PUT)
    public Result updateById(@RequestBody @Valid AccountTb accountTb);

}

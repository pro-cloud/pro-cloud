package com.cloud.account.controller;

import com.cloud.common.data.base.Result;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.account.beans.po.AccountTb;
import com.cloud.account.service.AccountTbService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
 *
 *
 * @author Aijm
 * @date 2020-03-15 18:16:02
 */
@RestController
@RequestMapping("/accounttb" )
@Api(value = "accounttb", tags = "accounttb管理")
public class AccountTbController {

    @Autowired
    private AccountTbService accountTbService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param accountTb
     * @return
     */
    @GetMapping("/page")
    public Result getAccountTbPage(Page page, AccountTb accountTb) {
        return Result.success(accountTbService.page(page, Wrappers.query(accountTb)));
    }


    /**
     * 通过id查询
     * @param id id
     * @return Result
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable("id") Long id) {
        return Result.success(accountTbService.getById(id));
    }

    /**
     * 新增
     * @param accountTb
     * @return Result
     */
    @PostMapping
    public Result save(@RequestBody @Valid AccountTb accountTb) {
        return Result.success(accountTbService.save(accountTb));
    }

    /**
     * 修改
     * @param accountTb
     * @return Result
     */
    @PutMapping
    public Result updateById(@RequestBody @Valid AccountTb accountTb) {
        AccountTb byId = accountTbService.getById(accountTb.getId());
        byId.setMoney(byId.getMoney()-accountTb.getMoney());
        return Result.success(accountTbService.updateById(byId));
    }

    /**
     * 通过id删除
     * @param id id
     * @return Result
     */
    @DeleteMapping("/{id}")
    public Result removeById(@PathVariable Long id) {
        return Result.success(accountTbService.removeById(id));
    }

}

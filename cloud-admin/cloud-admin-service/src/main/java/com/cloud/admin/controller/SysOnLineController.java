package com.cloud.admin.controller;


import com.cloud.common.data.base.BaseController;
import com.cloud.common.data.base.Result;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Map;

/**
 * @Author Aijm
 * @Description 在线用户的管理
 * @Date 2019/12/27
 */
@RestController
@RequestMapping("/online")
public class SysOnLineController extends BaseController {


    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 查询token
     *
     * @param params 分页参数
     * @return
     */
    @PreAuthorize("@pms.hasPermission('admin_online_view')")
    @PostMapping("/page")
    public Result tokenList(@RequestBody Map<String, Object> params) {
        //根据分页参数获取对应数据
        // todo
        return Result.success("");
    }

}

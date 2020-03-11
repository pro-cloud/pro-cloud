package com.cloud.admin.controller;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.common.controller.base.BaseController;
import com.cloud.common.util.base.Result;
import com.cloud.common.util.constant.PaginationConstants;
import com.cloud.common.util.var.RedisKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ConvertingCursor;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author Aijm
 * @Description 在线用户的管理
 * @Date 2019/12/27
 */
@RestController
@RequestMapping("/online")
public class SysOnLineController extends BaseController {

    private static final String PRO_OAUTH_ACCESS = RedisKeys.REDIS_TOKEN_KEY + "auth_to_access:*";

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

        List<String> pages = findKeysForPage(PRO_OAUTH_ACCESS, MapUtil.getInt(params, PaginationConstants.CURRENT)
                , MapUtil.getInt(params, PaginationConstants.SIZE));

        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        Page result = new Page(MapUtil.getInt(params, PaginationConstants.CURRENT), MapUtil.getInt(params, PaginationConstants.SIZE));
        result.setRecords(redisTemplate.opsForValue().multiGet(pages));
        result.setTotal(redisTemplate.keys(PRO_OAUTH_ACCESS).size());
        return Result.success(result);
    }


    private List<String> findKeysForPage(String patternKey, int pageNum, int pageSize) {
        ScanOptions options = ScanOptions.scanOptions().match(patternKey).build();
        RedisSerializer<String> redisSerializer = redisTemplate.getKeySerializer();
        Cursor cursor = (Cursor) redisTemplate.executeWithStickyConnection(redisConnection -> new ConvertingCursor<>(redisConnection.scan(options), redisSerializer::deserialize));
        List<String> result = new ArrayList<>();
        int tmpIndex = 0;
        int startIndex = (pageNum - 1) * pageSize;
        int end = pageNum * pageSize;

        assert cursor != null;
        while (cursor.hasNext()) {
            if (tmpIndex >= startIndex && tmpIndex < end) {
                result.add(cursor.next().toString());
                tmpIndex++;
                continue;
            }
            if (tmpIndex >= end) {
                break;
            }
            tmpIndex++;
            cursor.next();
        }
        return result;
    }
}

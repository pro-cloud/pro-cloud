package com.cloud.admin.service.impl;

import com.cloud.common.cache.annotation.RedisLock;
import com.cloud.common.cache.constants.RedisKeys;
import com.cloud.common.cache.redis.RedisDao;
import com.cloud.common.data.base.BaseService;
import com.cloud.admin.beans.po.SysTenant;
import com.cloud.admin.mapper.SysTenantMapper;
import com.cloud.admin.service.SysTenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 租户管理
 *
 * @author Aijm
 * @date 2020-05-25 13:32:23
 */
@Service
public class SysTenantServiceImpl extends BaseService<SysTenantMapper, SysTenant> implements SysTenantService {

    @Autowired
    private SysTenantMapper sysTenantMapper;

    @Autowired
    private RedisDao redisDao;

    /**
     * 获取下一个租户id
     *
     * @return
     */
    @Override
    @RedisLock(key = RedisKeys.TENTANT_ADD)
    public Integer getNextTenantId() {
        Long nextTenantId = redisDao.hmSetIncrement(RedisKeys.TENTANT_ADD, RedisKeys.TENTANT_ADD_HASH, 1L);
        // 可能是清空了的数据 得到的错误 计数器
        if (nextTenantId == 1) {
            Integer maxTenantId = sysTenantMapper.getNextTenantId();
            if (maxTenantId != null) {
                nextTenantId = redisDao.hmSetIncrement(RedisKeys.TENTANT_ADD, RedisKeys.TENTANT_ADD_HASH, maxTenantId.longValue());
            }

        }
        return nextTenantId.intValue();
    }
}

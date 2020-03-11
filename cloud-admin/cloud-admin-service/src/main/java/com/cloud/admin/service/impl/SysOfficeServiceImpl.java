package com.cloud.admin.service.impl;

import com.cloud.admin.beans.po.SysOffice;
import com.cloud.admin.mapper.SysOfficeMapper;
import com.cloud.admin.service.SysOfficeService;
import com.cloud.common.cache.annotation.Cache;
import com.cloud.common.cache.annotation.CacheClear;
import com.cloud.common.cache.annotation.CacheConf;
import com.cloud.common.cache.constants.CacheScope;
import com.cloud.common.data.base.TreeService;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * 机构表
 *
 * @author Aijm
 * @date 2019-08-25 20:45:42
 */
@Service
@CacheConf(scope = CacheScope.OFFICE)
public class SysOfficeServiceImpl extends TreeService<SysOfficeMapper, SysOffice> implements SysOfficeService {


    @Override
    @Cache(key = "#id")
    public SysOffice getById(Serializable id) {
        return super.getById(id);
    }

    @Override
    @CacheClear(key = "#id")
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    @CacheClear(key = "#entity.id")
    public boolean updateById(SysOffice entity) {
        return super.updateById(entity);
    }
}

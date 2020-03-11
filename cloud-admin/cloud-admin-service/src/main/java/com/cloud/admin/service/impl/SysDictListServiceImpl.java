package com.cloud.admin.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cloud.common.data.base.BaseService;
import com.cloud.admin.beans.po.SysDictList;
import com.cloud.admin.mapper.SysDictListMapper;
import com.cloud.admin.service.SysDictListService;
import com.cloud.common.cache.annotation.Cache;
import com.cloud.common.cache.annotation.CacheClear;
import com.cloud.common.cache.annotation.CacheConf;
import com.cloud.common.cache.constants.CacheScope;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 字典项list
 *
 * @author Aijm
 * @date 2019-09-05 19:52:37
 */
@Service
@CacheConf(scope = CacheScope.DICT_LIST)
public class SysDictListServiceImpl extends BaseService<SysDictListMapper, SysDictList> implements SysDictListService {


    @Override
    @Cache(key = "#typeCode" , expire = 0)
    public List<SysDictList> getDictListByType(String typeCode) {
        return super.list(Wrappers.<SysDictList>query()
                .lambda().eq(SysDictList::getTypeCode, typeCode));
    }

    @Override
    @CacheClear(key = "#entity.typeCode")
    public boolean save(SysDictList entity) {
        return super.save(entity);
    }


    @Override
    @CacheClear(key = "#entity.typeCode")
    public boolean updateById(SysDictList entity) {
        return super.updateById(entity);
    }

    @Override
    @CacheClear(key = "#sysDictList.typeCode")
    public boolean removeByDictList(SysDictList sysDictList) {
        return super.removeById(sysDictList.getId());
    }


}

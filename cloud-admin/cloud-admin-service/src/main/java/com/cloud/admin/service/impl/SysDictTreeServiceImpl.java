package com.cloud.admin.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cloud.admin.beans.po.SysDictTree;
import com.cloud.admin.mapper.SysDictTreeMapper;
import com.cloud.admin.service.SysDictTreeService;
import com.cloud.common.cache.annotation.Cache;
import com.cloud.common.cache.annotation.CacheClear;
import com.cloud.common.cache.annotation.CacheConf;
import com.cloud.common.cache.constants.CacheScope;
import com.cloud.common.data.base.TreeService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 字典表树
 *
 * @author Aijm
 * @date 2019-09-05 20:00:25
 */
@Service
@CacheConf(scope = CacheScope.DICT_TREE)
public class SysDictTreeServiceImpl extends TreeService<SysDictTreeMapper, SysDictTree> implements SysDictTreeService {



    @Override
    @Cache(key = "#typeCode", expire = 0)
    public List<SysDictTree> getDicTreeByType(String typeCode) {
        List<SysDictTree> list = super.list(Wrappers.<SysDictTree>query()
                .lambda().eq(SysDictTree::getTypeCode, typeCode)
                .orderByAsc(SysDictTree::getSort));
        return list;
    }

    @Override
    @CacheClear(key = "#entity.typeCode")
    public boolean save(SysDictTree entity) {
        return super.save(entity);
    }

    @Override
    @CacheClear(key = "#entity.typeCode")
    public boolean updateById(SysDictTree entity) {
        return super.updateById(entity);
    }

    @Override
    @CacheClear(key = "#sysDictTree.typeCode")
    public boolean removeByDictTree(SysDictTree sysDictTree) {
        return super.removeById(sysDictTree.getId());
    }

}

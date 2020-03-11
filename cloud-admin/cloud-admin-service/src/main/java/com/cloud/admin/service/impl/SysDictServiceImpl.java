package com.cloud.admin.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cloud.common.data.base.BaseService;
import com.cloud.admin.beans.dto.DictDTO;
import com.cloud.admin.beans.po.SysDict;
import com.cloud.admin.beans.po.SysDictList;
import com.cloud.admin.beans.po.SysDictTree;
import com.cloud.admin.mapper.SysDictListMapper;
import com.cloud.admin.mapper.SysDictMapper;
import com.cloud.admin.mapper.SysDictTreeMapper;
import com.cloud.admin.service.SysDictService;
import com.cloud.common.cache.annotation.CacheClear;
import com.cloud.common.cache.constants.CacheScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 字典表
 *
 * @author Aijm
 * @date 2019-09-05 16:53:21
 */
@Service
public class SysDictServiceImpl extends BaseService<SysDictMapper, SysDict> implements SysDictService {

    @Autowired
    private SysDictListMapper sysDictListMapper;
    @Autowired
    private SysDictTreeMapper sysDictTreeMapper;

    @Override
    @CacheClear(scope = CacheScope.DICT, key = "'*:'+"+"#sysDict.typeCode", pattern = true)
    public boolean removeByDict(SysDict sysDict) {
        if (DictDTO.DICT_LIST.equals(sysDict.getType())) {
            sysDictListMapper.delete(Wrappers.<SysDictList>query()
                    .lambda().eq(SysDictList::getTypeCode, sysDict.getTypeCode()));
        } else {
            sysDictTreeMapper.delete(Wrappers.<SysDictTree>query()
                    .lambda().eq(SysDictTree::getTypeCode, sysDict.getTypeCode()));
        }
        return super.removeById(sysDict.getId());
    }
}

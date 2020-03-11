package com.cloud.admin.service;

import com.cloud.common.data.base.IProService;
import com.cloud.admin.beans.po.SysDict;

/**
 * 字典表
 *
 * @author Aijm
 * @date 2019-09-05 16:53:21
 */
public interface SysDictService extends IProService<SysDict> {

    /**
     *  必须要有id 和 typeCode 因为要根据 typeCode
     *      清除缓存tree 或者list
     * @param sysDict
     * @return
     */
    boolean removeByDict(SysDict sysDict);
}

package com.cloud.admin.service;

import com.cloud.common.data.base.IProService;
import com.cloud.admin.beans.po.SysDictList;

import java.util.List;

/**
 * 字典项list
 *
 * @author Aijm
 * @date 2019-09-05 19:52:37
 */
public interface SysDictListService extends IProService<SysDictList> {

    /**
     * 根据typecode 查询
     * @param typeCode
     * @return
     */
    List<SysDictList> getDictListByType(String typeCode);

    /**
     *  必须要有id 和 typeCode 因为要根据 typeCode
     *      清除缓存list
     * @param sysDictList
     * @return
     */
    boolean removeByDictList(SysDictList sysDictList);

}

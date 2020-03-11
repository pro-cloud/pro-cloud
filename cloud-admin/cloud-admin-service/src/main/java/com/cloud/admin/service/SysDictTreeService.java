package com.cloud.admin.service;

import com.cloud.admin.beans.po.SysDictTree;
import com.cloud.common.data.base.ITreeService;

import java.util.List;

/**
 * 字典表树
 *
 * @author Aijm
 * @date 2019-09-05 20:00:25
 */
public interface SysDictTreeService extends ITreeService<SysDictTree> {


    /**
     * 根据typecode 查询
     * @param typeCode
     * @return
     */
    List<SysDictTree> getDicTreeByType(String typeCode);

    /**
     *  必须要有id 和 typeCode 因为要根据 typeCode
     *      清除缓存tree
     * @param sysDictTree
     * @return
     */
    boolean removeByDictTree(SysDictTree sysDictTree);
}

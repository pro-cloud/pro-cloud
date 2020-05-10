package com.cloud.oss.mapper;

import com.cloud.common.data.base.ProMapper;
import com.cloud.oss.beans.po.SysFile;

/**
 * 文件统一管理
 *
 * @author Aijm
 * @date 2019-09-11 17:24:43
 */
public interface SysFileMapper extends ProMapper<SysFile> {


    /**
     * 保存文件
     *
     * @param sysFile
     */
    public void saveFile(SysFile sysFile);
}

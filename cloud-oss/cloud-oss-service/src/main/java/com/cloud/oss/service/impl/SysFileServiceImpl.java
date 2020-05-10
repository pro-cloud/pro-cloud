package com.cloud.oss.service.impl;

import com.cloud.common.data.base.BaseService;
import com.cloud.common.data.util.ObjUtil;
import com.cloud.oss.beans.po.SysFile;
import com.cloud.oss.mapper.SysFileMapper;
import com.cloud.oss.service.SysFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 文件统一管理
 *
 * @author Aijm
 * @date 2019-09-11 17:24:43
 */
@Service
public class SysFileServiceImpl extends BaseService<SysFileMapper, SysFile> implements SysFileService {


    @Autowired
    private SysFileMapper sysFileMapper;

    /**
     * 保存文件
     *
     * @param sysFile
     */
    @Override
    public void saveFile(SysFile sysFile, Long userId) {
        ObjUtil.preInsert(sysFile, userId);
        sysFileMapper.saveFile(sysFile);
    }
}

package com.cloud.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.generator.entity.SysDataSourceConf;

/**
 * 数据源表
 * @author Aijm
 * @since 2019/6/16
 */
public interface SysDataSourceConfService extends IService<SysDataSourceConf> {
	/**
	 * 保存数据源并且加密
	 *
	 * @param sysDataSourceConf
	 * @return
	 */
	Boolean saveDsByEnc(SysDataSourceConf sysDataSourceConf);

	/**
	 * 更新数据源
	 *
	 * @param sysDataSourceConf
	 * @return
	 */
	Boolean updateDsByEnc(SysDataSourceConf sysDataSourceConf);
}

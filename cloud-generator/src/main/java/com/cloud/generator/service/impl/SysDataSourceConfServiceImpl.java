package com.cloud.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.generator.config.DynamicDataSourceConfig;
import com.cloud.generator.entity.SysDataSourceConf;
import com.cloud.generator.mapper.SysDataSourceConfMapper;

import com.cloud.generator.service.SysDataSourceConfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 数据源表
 * @author Aijm
 * @since 2019/6/16
 */
@Service("sysDataSourceConfService")
public class SysDataSourceConfServiceImpl extends ServiceImpl<SysDataSourceConfMapper, SysDataSourceConf> implements SysDataSourceConfService {
	@Autowired
	private DynamicDataSourceConfig dynamicDataSourceConfig;


	/**
	 * 保存数据源并且加密
	 *
	 * @param sysDataSourceConf
	 * @return
	 */
	@Override
	public Boolean saveDsByEnc(SysDataSourceConf sysDataSourceConf) {

		return dynamicDataSourceConfig.reload();
	}

	/**
	 * 更新数据源
	 *
	 * @param sysDataSourceConf
	 * @return
	 */
	@Override
	public Boolean updateDsByEnc(SysDataSourceConf sysDataSourceConf) {
		return dynamicDataSourceConfig.reload();
	}
}

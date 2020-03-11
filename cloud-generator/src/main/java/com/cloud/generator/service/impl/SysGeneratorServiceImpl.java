package com.cloud.generator.service.impl;

import cn.hutool.core.io.IoUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.generator.entity.GenConfig;
import com.cloud.generator.mapper.SysGeneratorMapper;
import com.cloud.generator.service.SysGeneratorService;
import com.cloud.generator.util.DynamicDataSourceContextHolder;
import com.cloud.generator.util.GenUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成器
 * @author Aijm
 * @since 2019/6/16
 */
@Service
@AllArgsConstructor
@Slf4j
public class SysGeneratorServiceImpl implements SysGeneratorService {
	private final SysGeneratorMapper sysGeneratorMapper;

	/**
	 * 分页查询表
	 *
	 * @param tableName 查询条件
	 * @param id
	 * @return
	 */
	@Override
	public IPage<List<Map<String, Object>>> getPage(Page page, String tableName, String id) {
		DynamicDataSourceContextHolder.setDataSourceType(id);
		return sysGeneratorMapper.queryList(page, tableName);
	}

	/**
	 * 生成代码
	 *
	 * @param genConfig 生成配置
	 * @return
	 */
	@Override
	public byte[] generatorCode(GenConfig genConfig) {
		DynamicDataSourceContextHolder.setDataSourceType(genConfig.getId());
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ZipOutputStream zip = new ZipOutputStream(outputStream);

		//查询表信息
		Map<String, String> table = queryTable(genConfig.getTableName());
		//查询列信息
		List<Map<String, String>> columns = queryColumns(genConfig.getTableName());
		//生成代码
		try {
			GenUtils.generatorCode(genConfig, table, columns, zip);
		} catch (Exception e) {
			log.error("生成代码保存", e);
		}
		IoUtil.close(zip);
		DynamicDataSourceContextHolder.clearDataSourceType();
		return outputStream.toByteArray();
	}

	private Map<String, String> queryTable(String tableName) {
		return sysGeneratorMapper.queryTable(tableName);
	}

	private List<Map<String, String>> queryColumns(String tableName) {
		return sysGeneratorMapper.queryColumns(tableName);
	}
}

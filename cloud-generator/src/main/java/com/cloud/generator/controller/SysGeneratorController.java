package com.cloud.generator.controller;

import cn.hutool.core.io.IoUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;


import com.cloud.common.util.base.Result;
import com.cloud.generator.entity.GenConfig;
import com.cloud.generator.service.SysGeneratorService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * 代码生成器
 * @author Aijm
 * @since 2019/6/16
 */
@RestController
@AllArgsConstructor
@RequestMapping("/generator")
public class SysGeneratorController {
	private final SysGeneratorService sysGeneratorService;

	/**
	 * 列表
	 *
	 * @param tableName 参数集
	 * @param id        数据源编号
	 * @return 数据库表
	 */
	@GetMapping("/page")
	public Result getPage(Page page, String tableName, String id) {
		return Result.success(sysGeneratorService.getPage(page, tableName, id));
	}

	/**
	 * 生成代码
	 */
	@SneakyThrows
	@GetMapping("/code")
	public void generatorCode(GenConfig genConfig, HttpServletResponse response) {
		byte[] data = sysGeneratorService.generatorCode(genConfig);
		response.reset();
		response.setHeader(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=%s.zip", genConfig.getTableName()));
		response.addHeader(HttpHeaders.CONTENT_LENGTH, String.valueOf(data.length));
		response.setContentType("application/octet-stream; charset=UTF-8");

		IoUtil.write(response.getOutputStream(), Boolean.TRUE, data);
	}
}

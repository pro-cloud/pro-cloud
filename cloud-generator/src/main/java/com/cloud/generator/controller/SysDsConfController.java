package com.cloud.generator.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.common.util.base.Result;
import com.cloud.generator.dto.SysDataSourceConfListDTO;
import com.cloud.generator.entity.SysDataSourceConf;
import com.cloud.generator.service.SysDataSourceConfService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 数据源管理
 * @author Aijm
 * @since 2019/6/16
 */
@RestController
@AllArgsConstructor
@RequestMapping("/dsconf")
public class SysDsConfController {

	private final SysDataSourceConfService sysDataSourceConfService;

	/**
	 * 分页查询
	 *
	 * @param page              分页对象
	 * @param sysDataSourceConf 数据源表
	 * @return
	 */
	@GetMapping("/page")
	public Result getSysDataSourceConfPage(Page page, SysDataSourceConf sysDataSourceConf) {
		return Result.success(sysDataSourceConfService.page(page, Wrappers.query(sysDataSourceConf)));
	}

	/**
	 * 查询全部数据源
	 * @return
	 */
	@GetMapping("/list")
	public Result list() {
		return Result.success(new SysDataSourceConfListDTO().setSysDataSourceConfs(sysDataSourceConfService.list()));
	}


	/**
	 * 通过id查询数据源表
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public Result getById(@PathVariable("id") String id) {
		return Result.success(sysDataSourceConfService.getById(id));
	}


	/**
	 * 新增数据源表
	 * @param sysDataSourceConf
	 * @return
	 */
	@PostMapping
	public Result save(@RequestBody SysDataSourceConf sysDataSourceConf) {
		return Result.success(sysDataSourceConfService.saveDsByEnc(sysDataSourceConf));
	}

	/**
	 * 修改数据源表
	 * @param sysDataSourceConf
	 * @return
	 */
	@PutMapping
	public Result<Boolean> updateById(@RequestBody SysDataSourceConf sysDataSourceConf) {
		return Result.success(sysDataSourceConfService.updateDsByEnc(sysDataSourceConf));
	}

	/**
	 * 通过id删除数据源表
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	public Result removeById(@PathVariable String id) {
		return Result.success(sysDataSourceConfService.removeById(id));
	}

}

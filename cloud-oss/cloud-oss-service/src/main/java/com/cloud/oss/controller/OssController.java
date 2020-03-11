package com.cloud.oss.controller;

import cn.hutool.core.util.StrUtil;

import com.cloud.common.data.util.FileDownUtil;
import com.cloud.common.oss.entity.CallBack;
import com.cloud.common.oss.entity.FileDown;
import com.cloud.common.oss.entity.FilePath;
import com.cloud.common.oss.entity.SignDTO;
import com.cloud.common.oss.props.FileProps;
import com.cloud.common.oss.util.FileOssDownUtil;
import com.cloud.common.oss.util.FileOssUploadUtil;
import com.cloud.common.util.base.Result;
import com.cloud.common.util.exception.BaseException;
import com.cloud.common.util.util.DateUtils;

import com.cloud.common.util.util.FileUtils;
import com.cloud.oss.beans.dto.SysFileDTO;
import com.cloud.oss.beans.po.SysFile;
import com.cloud.oss.service.SysFileService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author Aijm
 * @Description 阿里oss上传
 * @Date 2019/9/11
 */
@RestController
@RequestMapping("/oss")
@Slf4j
public class OssController {


	@Autowired
	private FileProps fileProps;

	@Autowired
	private SysFileService sysFileService;


	/**
	 *  签名生成  用浏览器直接上传时给js传递授权
	 * @param callback
	 * @param type 必填。防止恶意修改路径
	 * @return
	 */
	@CrossOrigin
	@GetMapping("/policy")
	public Result policy(@RequestParam(required = false) String callback, String type) {

		FilePath filePath = FileOssUploadUtil.getFilePath(fileProps.getFilePaths(), type);
		if (filePath == null) {
			return Result.error("没有对应的文件类型");
		}
		if (StringUtils.isNotBlank(callback)) {
			filePath.setCallback(callback);
		}
		SignDTO policy = FileOssUploadUtil.policy(filePath);
		return Result.success(policy);
	}


	/**
	 * 上传成功回调方法
	 * @param callBack
	 * @return
	 */
	@RequestMapping("callback")
	public Result callback(CallBack callBack) {

		String filename = FileUtils.getName(callBack.getFilePath());
		String ossFileUrl = FileOssUploadUtil.getOssFileUrl(callBack.getFilePath());
		String fileSuffix = FileUtils.getFileSuffix(filename);
		SysFile sysFile = SysFile.builder()
				.belongName(callBack.getBelongName())
				.belongType(callBack.getBelongType())
				.fileName(filename)
				.fileUrl(ossFileUrl)
				.filePath(callBack.getFilePath())
				.type(FileUtils.getFileType(fileSuffix))
				.prePath(callBack.getPrePath())
				.fileSuffix(fileSuffix)
				.fileSize(callBack.getSize())
				.belongStatus(SysFileDTO.STATUS_ON)
				.build();
		if (FileUtils.IMAGE_TYPE.equals(sysFile.getType())) {
			sysFile.setProps(callBack.getWidth()+"*"+callBack.getHeight());
		}
		sysFileService.save(sysFile);
		return Result.success(callBack);
	}


	/**
	 *  根据单个或多个文件进行 zip下载
	 *  	文件路径和id都有 优先处理url的 默认名称yyyyMMddHHmmss.zip;
	 *  	可以根据文件id 或者路径下载；建议使用路径下载
	 *
	 * @param fileDown
	 */
	@GetMapping("downFilesZip")
	public void downFilesZip(FileDown fileDown, HttpServletResponse response) {

		// 处理文件路径
		List<String> urls = Lists.newArrayList();
		if (StrUtil.isBlank(fileDown.getUrls())) {
			String[] ids = fileDown.getIds().split(",");
			for (String id : ids) {
				SysFile byId = sysFileService.getById(id);
				urls.add(byId.getFilePath());
			}
		} else {
			urls = StrUtil.splitTrim(fileDown.getUrls(), ",");
		}
		// 处理默认fileName
		String fileName = fileDown.getFileName();
		if (StrUtil.isBlank(fileName)) {
			fileName = DateUtils.getCurrentShortDateTimeStr().concat(".zip");
		}
		byte[] bytes = FileOssDownUtil.writeZip(urls);
		try {
			FileDownUtil.write(response, fileName, bytes);
		} catch (IOException e) {
			log.info("出现异常！");
			throw new BaseException("出现异常！");
		}

	}


	/**
	 * 根据文件 id 或者路径下载 文件
	 * 	文件路径和id都有 优先处理url的 默认名称为原文件名称
	 * @param fileDown
	 * @param response
	 * @throws IOException
	 */
	@GetMapping("downFile")
	public void downFiles(FileDown fileDown, HttpServletResponse response) throws IOException {

		String url = fileDown.getUrls();

		if (StrUtil.isBlank(url)) {
			SysFile byId = sysFileService.getById(fileDown.getIds());
			url = byId.getFilePath();
		}
		String fileName = fileDown.getFileName();
		if (StrUtil.isBlank(fileName)) {
			fileName = FileUtils.getName(url);
		}
		FileOssDownUtil.write(response, fileName, url);
	}


}

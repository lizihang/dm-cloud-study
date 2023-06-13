package com.dm.study.cloud.controller;

import com.dm.study.cloud.param.FileUploadParams;
import com.dm.study.cloud.service.FileUploadService;
import com.dm.study.cloud.vo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
/**
 * <p>标题：文件上传controller</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2023年06月13日 15:24</p>
 * <p>类全名：com.dm.study.cloud.controller.FileUploadController</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@RestController
@RequestMapping("/file")
public class FileUploadController {
	@Resource
	FileUploadService service;

	/**
	 * 文件上传
	 * @param file
	 * @return
	 */
	@PostMapping("/upload")
	public Result upload(@RequestParam("file") MultipartFile file, FileUploadParams params) {
		service.upload(file, params);
		return Result.success("文件上传成功！");
	}

	/**
	 * 多文件上传
	 * @param files
	 * @return
	 */
	@PostMapping("/uploadFiles")
	public Result uploadFiles(@RequestParam("files") MultipartFile[] files, FileUploadParams params) {
		service.uploadFiles(files, params);
		return Result.success("文件上传成功！");
	}

	/**
	 * @return
	 */
	@PostMapping("/listFiles")
	public Result listFiles() {
		return Result.success("查询成功！", service.listFiles());
	}
}

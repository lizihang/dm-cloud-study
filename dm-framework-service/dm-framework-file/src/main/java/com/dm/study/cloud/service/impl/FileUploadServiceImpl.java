package com.dm.study.cloud.service.impl;

import com.dm.study.cloud.param.FileUploadParams;
import com.dm.study.cloud.service.FileUploadService;
import com.dm.study.cloud.util.MinioUtil;
import io.minio.messages.Item;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2023年06月13日 15:34</p>
 * <p>类全名：com.dm.study.cloud.service.impl.FileUploadServiceImpl</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Service
public class FileUploadServiceImpl implements FileUploadService {
	@Resource
	MinioUtil minioUtil;
	@Override
	public void upload(MultipartFile file, FileUploadParams params) {
		String upload = minioUtil.upload(file, "minio-study");
	}

	@Override
	public void uploadFiles(MultipartFile[] files, FileUploadParams params) {
		// TODO
	}

	@Override
	public List<Item> listFiles() {
		return minioUtil.listObjects("minio-study");
	}
}

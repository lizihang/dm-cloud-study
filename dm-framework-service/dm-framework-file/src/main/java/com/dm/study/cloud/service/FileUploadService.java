package com.dm.study.cloud.service;

import com.dm.study.cloud.param.FileUploadParams;
import io.minio.messages.Item;
import org.springframework.web.multipart.MultipartFile;

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
 * <p>类全名：com.dm.study.cloud.service.FileUploadService</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public interface FileUploadService {
	void upload(MultipartFile file, FileUploadParams params);

	void uploadFiles(MultipartFile[] files, FileUploadParams params);

	List<Item> listFiles();
}

package com.dm.study.cloud.controller;

import com.dm.study.cloud.util.MinioUtil;
import com.dm.study.cloud.vo.Result;
import io.minio.messages.Item;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
 * <p>创建日期：2023年05月07日 13:00</p>
 * <p>类全名：com.dm.study.cloud.controller.MinioTestController</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@RestController
@RequestMapping("/minio")
public class MinioTestController {
    @Resource
    MinioUtil minioUtil;

    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public Result queryMenuList(MultipartFile file) {
        String upload = minioUtil.upload(file, "minio-study");
        return Result.success("文件上传成功！路径为：" + upload);
    }

    /**
     *
     * @return
     */
    @PostMapping("/listFiles")
    public Result queryMenuList() {
        List<Item> items = minioUtil.listObjects("minio-study");
        return Result.success("文件列表：" + items);
    }
}

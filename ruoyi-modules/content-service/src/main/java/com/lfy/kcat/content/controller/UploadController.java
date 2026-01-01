package com.lfy.kcat.content.controller;

import com.lfy.kcat.content.minio.template.MinioTemplate;
import org.dromara.common.core.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传
 * 前端访问路由地址为:/content/upload
 *
 * @author leifengyang
 * @version 1.0
 * @date 2025/9/5 10:37
 * @description:
 */
@RestController
public class UploadController {

    @Autowired
    MinioTemplate minioTemplate;

    /**
     * 文件上传
     * @param file 文件项
     * @return
     */
    @PostMapping("/upload")
    public R upload(@RequestParam("file") MultipartFile file){
        //1、上传到minio；并获取到文件的url地址
        String url = minioTemplate.uploadWebFile(file);

        return R.ok("success",url);
    }
}

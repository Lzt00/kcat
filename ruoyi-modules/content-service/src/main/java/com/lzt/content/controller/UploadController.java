package com.lzt.content.controller;

import com.lzt.content.minio.service.impl.UploadService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传
 * 前端访问路径为 content/upload
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/upload")
public class UploadController {


    private final UploadService uploadService;

    /**
     * 文件上传接口
     * @param file 上传的文件
     * @return
     */
    @PostMapping
    public R upload(MultipartFile file){
        String url = uploadService.upload(file);
        return R.ok("success",url);
    }

}

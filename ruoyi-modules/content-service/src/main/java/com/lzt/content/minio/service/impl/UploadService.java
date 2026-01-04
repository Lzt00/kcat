package com.lzt.content.minio.service.impl;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    String upload(MultipartFile multipartFile);
}

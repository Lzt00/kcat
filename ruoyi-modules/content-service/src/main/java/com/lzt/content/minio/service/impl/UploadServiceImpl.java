package com.lzt.content.minio.service.impl;

import com.lzt.content.minio.config.MyMinIOClient;
import com.lzt.content.minio.config.MyMinIOProperties;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.extern.slf4j.Slf4j;
import org.dromara.common.core.enums.FormatsType;
import org.dromara.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;


@Service
@Slf4j
public class UploadServiceImpl implements UploadService {

    @Autowired
    private MinioClient minioClient;
    @Autowired
    private MyMinIOProperties minIOProperties;

    @Override
    public String upload(MultipartFile multipartFile)  {
        String bucketName="kcat";
        String filename =
            DateUtils.parseDateToStr(FormatsType.YYYY_MM_DD_SLASH,new Date())+"/"+ UUID.randomUUID()+"_"+
            multipartFile.getOriginalFilename();
        InputStream inputStream = null;
        String url ="";
        try {
            inputStream = multipartFile.getInputStream();
              long size = multipartFile.getSize();

            //创建桶
            if (!(minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build()))) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
                log.info("桶{}创建成功",bucketName);
            }else{
                log.info("桶{}已经存在",bucketName);
            }
            //上传文件

            PutObjectArgs objectArgs = PutObjectArgs.builder().bucket(bucketName).object(filename)
                .stream(inputStream, size, -1)
                .contentType("image/jpeg").build();
            minioClient.putObject(objectArgs);
            url=minIOProperties.getEndpoint()+"/"+bucketName+"/"+filename;
            log.info("文件上传成功！");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        return url;
    }
}

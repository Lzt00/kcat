package com.lfy.kcat.content.minio.template;

import com.lfy.kcat.content.minio.properties.MyMinioProperties;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import lombok.extern.slf4j.Slf4j;
import org.dromara.common.core.enums.FormatsType;
import org.dromara.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.UUID;

/**
 * @author leifengyang
 * @version 1.0
 * @date 2025/9/5 10:48
 * @description:
 */
@Slf4j
@Component
public class MinioTemplate {


    @Autowired
    MinioClient minioClient;

    @Autowired
    MyMinioProperties minioProperties;

    private final String BUCKET_NAME = "kcat";

    /**
     * 上传web请求过来的文件
     * @param file  浏览器把文件所有信息都在这里封面
     * @return
     */
    public String uploadWebFile(MultipartFile file) {
        String url = "";
        try {
            //获取文件名
            String fileName = file.getOriginalFilename();
            //获取文件内容类型
            String contentType = file.getContentType();
            //文件大小
            long size = file.getSize();

            //上传到指定桶
            bucketExistAndCreate(BUCKET_NAME);
            //当前日期作为文件夹路径
            String path = DateUtils.parseDateToStr(FormatsType.YYYY_MM_DD_SLASH, new Date());

            //文件名：年/月/日/uuid_原始名
            String objectName = path + "/" + UUID.randomUUID().toString() + "_" + fileName;
            //上传文件
            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                .bucket(BUCKET_NAME)
                .stream(file.getInputStream(), size, -1)
                .contentType(contentType)
                .object(objectName)
                .build();
            minioClient.putObject(putObjectArgs);


            //返回文件的访问地址；

            String endpoint = minioProperties.getEndpoint();

            url = endpoint+"/"+BUCKET_NAME+"/"+objectName;


        }catch (Exception e){
            throw  new RuntimeException(e);
        }

        //返回文件的访问地址；
        return url;
    }

    public void bucketExistAndCreate(String bucketName) {
        BucketExistsArgs args = BucketExistsArgs.builder()
            .bucket(bucketName)
            .build();

        try {
            boolean exists = minioClient.bucketExists(args);

            if (!exists) {
                //不存在，则创建
                MakeBucketArgs bucketArgs = MakeBucketArgs.builder()
                    .bucket(bucketName)
                    .build();
                minioClient.makeBucket(bucketArgs);
                log.info("bucket {} 新创建完成", bucketName);
            }
        } catch (Exception e) {
            //写业务用下面标准
            //金标准：吃掉哪些编译时异常【代码整洁】，
            //      然后用运行时抛出去（保证异常机制还在：全局异常处理器还能处理）
            //写底层框架了。声明式抛异常，让调用自己决定怎么办
            throw new RuntimeException(e);
        }

    }
}

package com.lzt.content;


import io.minio.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;

@Slf4j
public class MinIOTest {


    @Test
    public void test() throws Exception{
        String bucketName="kcat";
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\lizetao\\Desktop\\avater.jpg");
        MinioClient minioClient = MinioClient.builder().endpoint("http://192.168.101.200:9000").credentials("ruoyi", "ruoyi123").build();
        //创建桶
        if (!(minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build()))) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            log.info("桶{}创建成功",bucketName);
        }else{
            log.info("桶{}已经存在",bucketName);
        }
        //上传文件

        PutObjectArgs objectArgs = PutObjectArgs.builder().bucket(bucketName).object("avater.jpg")
            .stream(fileInputStream, fileInputStream.available(), -1)
            .contentType("image/jpeg").build();
        minioClient.putObject(objectArgs);
        log.info("文件上传成功！");

    }

}

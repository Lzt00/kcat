package com.lfy.kcat.content;

import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author leifengyang
 * @version 1.0
 * @date 2025/9/5 10:06
 * @description:
 */
public class MinioTest {

    @Test
    void minioTest() throws Exception {
        String bucketName = "kcat";
        String path = "E:\\2022年必应壁纸365张全打包下载\\必应壁纸2022年11月\\2.JPG";



        //1、创建一个MinioClient 连接Minio服务器
        MinioClient client = MinioClient.builder()
            .endpoint("http://localhost:9000")
            .credentials("ruoyi", "ruoyi123")
            .build();

        System.out.println(client);

        //2、准备一个桶 Bucket
        //1)、判断 kcat 桶是否存储。如果不存在则创建
        BucketExistsArgs args = BucketExistsArgs.builder()
            .bucket(bucketName)
            .build();
        boolean exists = client.bucketExists(args);
        if (!exists) {
            MakeBucketArgs bucketArgs = MakeBucketArgs.builder()
                .bucket(bucketName)
                .build();
            client.makeBucket(bucketArgs);
            System.out.println(bucketName+ " bucket 创建成功");
        }else {
            System.out.println(bucketName+ " 已经存在，无需创建");
        }

        FileInputStream stream = new FileInputStream(path);
        String fileName = UUID.randomUUID().toString()+"_2.JPG";
        //3、上传文件流
        PutObjectArgs putObjectArgs = PutObjectArgs.builder()
            .bucket(bucketName)
            .stream(stream, stream.available(), -1) //指定文件流
            .object(fileName) //对象名，就是将来的文件名
            .contentType("image/jpeg") //指定文件的内容类型
            .build();
        //上传文件
        client.putObject(putObjectArgs);


        System.out.println("文件上传完成....");
        //文件访问地址是什么？
        //1、私有桶： 必须获取一个分享的连接。这个连接有有效期。
        //获取预签名的对象url
        GetPresignedObjectUrlArgs urlArgs = GetPresignedObjectUrlArgs.builder()
            .method(Method.GET)  //请求方式
            .bucket(bucketName)  //桶名
            .object(fileName)  //对象名
            .expiry(1, TimeUnit.DAYS)  //过期事件
            .build();
        String objectUrl = client.getPresignedObjectUrl(urlArgs);
        System.out.println("私有桶：预签名地址："+objectUrl);


        //2、公有桶: 公共随意访问。所以无需签名； 直接拼
        // minio服务器/桶/文件
        String publicUrl = "http://localhost:9000/"+bucketName+"/"+fileName;
        System.out.println("公有桶：直接访问地址："+publicUrl);


        //3、自定义权限桶： 增删改操作私有（必须在后台登录）、查公有



    }
}

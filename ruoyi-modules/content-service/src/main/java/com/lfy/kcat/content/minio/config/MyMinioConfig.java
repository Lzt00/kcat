package com.lfy.kcat.content.minio.config;

import com.lfy.kcat.content.minio.properties.MyMinioProperties;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author leifengyang
 * @version 1.0
 * @date 2025/9/5 10:43
 * @description:
 */
@Configuration
public class MyMinioConfig {


    @Autowired
    MyMinioProperties minioProperties;

    @Bean
    MinioClient minioClient() {
        return MinioClient.builder()
            .endpoint(minioProperties.getEndpoint())
            .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
            .build();
    }
}

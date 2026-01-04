package com.lzt.content.minio.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyMinIOClient {

    @Autowired
    private MyMinIOProperties minIOProperties;

    @Bean
    public MinioClient minioClient(){
        return MinioClient.builder().endpoint(minIOProperties.getEndpoint())
            .credentials(minIOProperties.getAccessKey(), minIOProperties.getSecretKey())
            .build();
    }
}

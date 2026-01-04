package com.lzt.content.minio.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "myminio")
@Component
@Data
public class MyMinIOProperties {
    private String endpoint;
    private String accessKey;
    private String secretKey;
}

package com.lfy.kcat.content.minio.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author leifengyang
 * @version 1.0
 * @date 2025/9/5 10:41
 * @description:
 */
@Component
@ConfigurationProperties(prefix = "myminio")
@Data
public class MyMinioProperties {

   private String endpoint;
   private String  accessKey;
   private String  secretKey;
}

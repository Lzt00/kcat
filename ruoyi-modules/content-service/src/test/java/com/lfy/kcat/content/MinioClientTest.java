package com.lfy.kcat.content;

import io.minio.MinioClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author leifengyang
 * @version 1.0
 * @date 2025/9/5 10:45
 * @description:
 */
@SpringBootTest
public class MinioClientTest {

    @Autowired
    MinioClient minioClient;

    @Test
    public void test01(){
        //断言机制。说明测试成功与失败
        Assertions.assertNotNull(minioClient);
    }
}

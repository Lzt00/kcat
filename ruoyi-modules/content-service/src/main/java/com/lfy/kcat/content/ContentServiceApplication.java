package com.lfy.kcat.content;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;

/**
 * @author leifengyang
 * @version 1.0
 * @date 2025/9/4 11:23
 * @description:
 */
@SpringBootApplication
public class ContentServiceApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ContentServiceApplication.class);
        application.setApplicationStartup(new BufferingApplicationStartup(2048));
        application.run(args);
        System.out.println("(♥◠‿◠)ﾉﾞ  内容服务模块 启动成功   ლ(´ڡ`ლ)ﾞ  ");
    }
}

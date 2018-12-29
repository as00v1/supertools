package com.qiaohx.encrypt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan("com.qiaohx.encrypt.mapper")
public class EncryptApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(EncryptApplication.class, args);
    }

    /**
     * 如果要打war包，需重写configure方法<br/>
     * 注意：<br/>
     * server.port= xx 和 server.servlet.context-path= xx 将失效
     * @param builder
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(EncryptApplication.class);
    }
}

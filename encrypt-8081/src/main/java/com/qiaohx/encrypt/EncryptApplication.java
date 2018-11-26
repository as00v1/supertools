package com.qiaohx.encrypt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.qiaohx.encrypt.mapper")
public class EncryptApplication {

    public static void main(String[] args) {
        SpringApplication.run(EncryptApplication.class, args);
    }
}

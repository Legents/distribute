package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.example.demo.mapper")
@EnableTransactionManagement(proxyTargetClass = true)
@EnableCaching
public class ShangHaiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShangHaiApplication.class, args);
    }

}

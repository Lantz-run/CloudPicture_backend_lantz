package com.lantz.lantzpicturebackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@MapperScan("com.lantz.lantzpicturebackend.mapper")
@EnableAspectJAutoProxy(exposeProxy = true)
public class LantzPictureBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(LantzPictureBackendApplication.class, args);
    }

}

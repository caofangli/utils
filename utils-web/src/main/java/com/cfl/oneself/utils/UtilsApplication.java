package com.cfl.oneself.utils;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName： UtilsApplication
 * @Description： TODO:
 * @Author： cfl
 * @Date: Created in 19:34 2019/11/30
 * @Vesion 1.0
 */
@SpringBootApplication(scanBasePackages = "com.cfl.oneself.utils")
@ComponentScan(basePackages = {"com.cfl.oneself.utils.service","com.cfl.oneself.utils.controller"})
@MapperScan("com.cfl.oneself.utils.dao")
public class UtilsApplication {

    public static void main(String[] args) {
        SpringApplication.run(UtilsApplication.class, args);
    }

}

package com.zhangyue.laboratory;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Zhang Yong
 * @date 2019-12-02
 */
@SpringBootApplication
@MapperScan(basePackages = "com.zhangyue.laboratory.mapper")
public class LaboratoryApplication {
    public static void main(String[] args) {
        SpringApplication.run(LaboratoryApplication.class, args);
    }
}

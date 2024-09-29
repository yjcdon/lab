package com.lab;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@ComponentScan(basePackages = {"com.lab.*"})
@MapperScan(basePackages = {"com.lab.mapper"})
@EnableAsync
public class LabApplication {

    public static void main (String[] args) {
        SpringApplication.run(LabApplication.class, args);
    }

}

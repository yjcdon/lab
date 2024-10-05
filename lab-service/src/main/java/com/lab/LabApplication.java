package com.lab;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@ComponentScan(basePackages = {"com.lab.*"})
@MapperScan(basePackages = {"com.lab.mapper"})
@EnableAsync
@EnableDiscoveryClient
public class LabApplication {

    public static void main (String[] args) {
        SpringApplication.run(LabApplication.class, args);
    }

}

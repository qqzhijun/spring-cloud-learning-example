package com.lidong.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 开启服务发现
 */


@EnableEurekaServer
@SpringBootApplication
public class SpringCloudRegister {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudRegister.class, args);
    }

}


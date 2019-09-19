package com.lidong.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 开启服务发现
 */

@EnableDiscoveryClient
@SpringBootApplication
public class SpringCloudNacosConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudNacosConfigApplication.class, args);
    }

}


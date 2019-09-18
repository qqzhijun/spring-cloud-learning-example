package com.lidong.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 开启服务发现
 */


@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudLidongProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudLidongProviderApplication.class, args);


    }

}


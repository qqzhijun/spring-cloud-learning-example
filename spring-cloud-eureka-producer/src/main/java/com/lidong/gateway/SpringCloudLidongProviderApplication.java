package com.lidong.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 开启服务发现
 */


@SpringBootApplication
//@EnableDiscoveryClient
//@EnableEurekaClient
//注意：早期的版本（Dalston及更早版本）还需在启动类上添加注解@EnableDiscoveryClient 或@EnableEurekaClient ，从Edgware开始，该注解可省略。
public class SpringCloudLidongProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudLidongProviderApplication.class, args);
    }

}


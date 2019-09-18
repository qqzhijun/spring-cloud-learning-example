package com.lidong.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
/**
 * 开启服务发现
 */


@EnableDiscoveryClient
@EnableAdminServer
@SpringBootApplication
public class SpringCloudAdmin {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudAdmin.class, args);
    }
}


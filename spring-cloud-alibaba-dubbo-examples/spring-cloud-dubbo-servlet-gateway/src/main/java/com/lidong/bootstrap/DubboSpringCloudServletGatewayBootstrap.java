package com.lidong.bootstrap;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients
@ServletComponentScan(basePackages = "com.lidong.gateway")
@SpringBootApplication
public class DubboSpringCloudServletGatewayBootstrap {

	public static void main(String[] args) {
		new SpringApplicationBuilder(DubboSpringCloudServletGatewayBootstrap.class)
				.properties("spring.profiles.active=nacos").run(args);
	}
}

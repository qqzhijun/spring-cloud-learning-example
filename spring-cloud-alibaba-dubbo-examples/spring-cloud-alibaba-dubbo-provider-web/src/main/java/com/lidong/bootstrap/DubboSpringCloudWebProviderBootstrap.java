package com.lidong.bootstrap;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Dubbo Spring Cloud Provider Bootstrap
 */
@EnableDiscoveryClient
@EnableAutoConfiguration
public class DubboSpringCloudWebProviderBootstrap {

	public static void main(String[] args) {
		new SpringApplicationBuilder(DubboSpringCloudWebProviderBootstrap.class)
				.properties("spring.profiles.active=nacos").web(WebApplicationType.NONE)
				.run(args);
	}
}

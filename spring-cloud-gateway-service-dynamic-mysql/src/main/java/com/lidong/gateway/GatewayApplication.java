package com.lidong.gateway;

import com.lidong.gateway.repository.MysqlRouteDefinitionRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.InMemoryRouteDefinitionRepository;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 开启服务发现
 */

@EnableDiscoveryClient
@EnableJpaRepositories(basePackages = "com.lidong.gateway")
@EntityScan(basePackages = {"com.lidong.gateway.entity"})
@SpringBootApplication
public class GatewayApplication {

    /**
     *
     * @return
     */
    @Bean
    public RouteDefinitionWriter routeDefinitionWriter() {
       return new InMemoryRouteDefinitionRepository();
    }

    @Bean
    public MysqlRouteDefinitionRepository routeDefinitionRepository(){
        return new MysqlRouteDefinitionRepository();
    }

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

}


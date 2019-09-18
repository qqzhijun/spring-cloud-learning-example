package com.lidong.gateway;

import com.lidong.gateway.repository.RedisRouteDefinitionRepository;
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
 *
 *  CachingRouteDefinitionLocator 缓存目标
 * CompositeRouteDefinitionLocator 组合多种
 * DiscoveryClientRouteDefinitionLocator 从注册中心
 * InMemoryRouteDefinitionRepository 读取内存中的
 * PropertiesRouteDefinitionLocator 读取配置文件 GatewayProperties yml/properties
 * RouteDefinitionRepository  从存储器读取
 *
 * @Bean
 * 	@ConditionalOnMissingBean(RouteDefinitionRepository.class)
 * 	public InMemoryRouteDefinitionRepository inMemoryRouteDefinitionRepository() {
 * 		return new InMemoryRouteDefinitionRepository();
 * 	}
 * 通过上面代码，可以看到如果没有RouteDefinitionRepository的实例，
 * 则默认用InMemoryRouteDefinitionRepository。而做动态路由的关键就在这里。
 * 即通过自定义的RouteDefinitionRepository类，来提供路由配置信息。
 *
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
       return new RedisRouteDefinitionRepository();
    }


    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

}


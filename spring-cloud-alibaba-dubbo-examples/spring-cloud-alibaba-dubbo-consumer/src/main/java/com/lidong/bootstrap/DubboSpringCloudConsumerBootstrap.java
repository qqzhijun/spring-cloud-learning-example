package com.lidong.bootstrap;

import com.lidong.dubbo.service.RestService;
import com.lidong.dubbo.service.User;
import com.lidong.dubbo.service.UserService;
import org.apache.dubbo.config.annotation.Reference;

import com.alibaba.cloud.dubbo.annotation.DubboTransported;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Dubbo Spring Cloud Consumer Bootstrap
 */
@EnableDiscoveryClient
@EnableFeignClients
@EnableScheduling
@EnableCaching
@SpringBootApplication(scanBasePackages = "com.lidong")
public class DubboSpringCloudConsumerBootstrap {

    @Reference
    private UserService userService;

    @Reference(version = "1.0.0", protocol = "dubbo")
    private RestService restService;

    @Autowired
    @Lazy
    private DubboFeignRestService dubboFeignRestService;


    @Autowired
    @Lazy
    private FeignRestService feignRestService;

    @Value("${provider.application.name}")
    private String providerApplicationName;

    @Autowired
    @LoadBalanced
    private RestTemplate restTemplate;

    @Bean
    public ApplicationRunner userServiceRunner() {
        return arguments -> {

            User user = new User();
            user.setId(1L);
            user.setName("小东哥");
            user.setAge(28);

            // save User
            System.out.printf("UserService.save(%s) : %s\n", user,
                    userService.save(user));

            // find all Users
            System.out.printf("UserService.findAll() : %s\n", user,
                    userService.findAll());

            // remove User
            System.out.printf("UserService.remove(%d) : %s\n", user.getId(),
                    userService.remove(user.getId()));

        };
    }

    @Bean
    public ApplicationRunner callRunner() {
        return arguments -> {
            callAll();
        };
    }

    private void callAll() {

        // To call /path-variables
        callPathVariables();

        // To call /headers
        callHeaders();

        // To call /param
        callParam();

        // To call /params
        callParams();

        // To call /request/body/map
        callRequestBodyMap();
    }

    @Scheduled(fixedDelay = 10 * 1000L)
    public void onScheduled() {
        callAll();
    }

    private void callPathVariables() {
        // Dubbo Service call
        System.out.println(restService.pathVariables("a", "b", "c"));
        // Spring Cloud Open Feign REST Call (Dubbo Transported)
        System.out.println(dubboFeignRestService.pathVariables("c", "b", "a"));
        // Spring Cloud Open Feign REST Call
//		System.out.println(feignRestService.pathVariables("b", "a", "c"));

        // RestTemplate call
        ResponseEntity<String> forEntity = restTemplate.getForEntity(
                "http://" + providerApplicationName + "//path-variables/{p1}/{p2}?v=c",
                String.class, "a", "b");
        System.out.println(forEntity);
    }

    private void callHeaders() {
        // Dubbo Service call
        System.out.println(restService.headers("a", "b", 10));
        // Spring Cloud Open Feign REST Call (Dubbo Transported)
        System.out.println(dubboFeignRestService.headers("b", 10, "a"));
        // Spring Cloud Open Feign REST Call
//		System.out.println(feignRestService.headers("b", "a", 10));
    }

    private void callParam() {
        // Dubbo Service call
        System.out.println(restService.param("lidong"));
        // Spring Cloud Open Feign REST Call (Dubbo Transported)
        System.out.println(dubboFeignRestService.param("lidong"));
        // Spring Cloud Open Feign REST Call
//		System.out.println(feignRestService.param("lidong"));
    }

    private void callParams() {
        // Dubbo Service call
        System.out.println(restService.params(1, "1"));
        // Spring Cloud Open Feign REST Call (Dubbo Transported)
        System.out.println(dubboFeignRestService.params("1", 1));
        // Spring Cloud Open Feign REST Call
//		System.out.println(feignRestService.params("1", 1));

        // RestTemplate call
        System.out.println(restTemplate.getForEntity(
                "http://" + providerApplicationName + "/param?param=小东哥", String.class));
    }

    private void callRequestBodyMap() {

        Map<String, Object> data = new HashMap<>();
        data.put("id", 1);
        data.put("name", "小东哥");
        data.put("age", 28);

        // Dubbo Service call
        System.out.println(restService.requestBodyMap(data, "Hello,World"));
        // Spring Cloud Open Feign REST Call (Dubbo Transported)
        System.out.println(dubboFeignRestService.requestBody("Hello,World", data));
        // Spring Cloud Open Feign REST Call
//		System.out.println(feignRestService.requestBody("Hello,World", data));

        // RestTemplate call
        System.out.println(restTemplate.postForObject(
                "http://" + providerApplicationName + "/request/body/map?param=小东哥", data,
                User.class));
    }

    @Bean
    @LoadBalanced
    @DubboTransported
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(DubboSpringCloudConsumerBootstrap.class)
                .properties("spring.profiles.active=nacos").run(args);
    }
}

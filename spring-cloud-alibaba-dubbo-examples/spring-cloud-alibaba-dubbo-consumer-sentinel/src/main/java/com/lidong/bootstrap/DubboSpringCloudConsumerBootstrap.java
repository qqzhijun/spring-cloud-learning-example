package com.lidong.bootstrap;

import com.alibaba.cloud.dubbo.annotation.DubboTransported;
import com.alibaba.cloud.sentinel.annotation.SentinelRestTemplate;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.SentinelRpcException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.lidong.dubbo.service.User;
import com.lidong.dubbo.service.UserService;
import org.apache.dubbo.config.ConsumerConfig;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

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
    UserService userService;

    @Bean
    public ConsumerConfig consumerConfig() {
        ConsumerConfig consumerConfig = new ConsumerConfig();
        consumerConfig.setFilter("-sentinel.dubbo.consumer.filter");
        return consumerConfig;
    }

    @Bean
    public Converter myConverter() {
        return new JsonFlowRuleListConverter();
    }
    /**
     *
     * @return
     */
    @Bean
    @SentinelRestTemplate(blockHandler = "handleException", blockHandlerClass = ExceptionUtil.class)//应用启动的时候会检查 @SentinelRestTemplate 注解对应的限流或降级方法是否存在，如不存在会抛出异常
    @LoadBalanced
    @DubboTransported
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    @LoadBalanced
    private RestTemplate restTemplate;

    @Value("${provider.application.name}")
    private String providerApplicationName;


    @Bean
    public ApplicationRunner callRunner() {
        return arguments -> {
            for (int i = 0; i < 500; i++) {
                System.out.println(restTemplate.getForEntity(
                        "http://" + providerApplicationName + "/param?param=小东哥", String.class));
            }
        };
    }

    public static void main(String[] args) {
        FlowRule flowRule = new FlowRule();
        flowRule.setResource("hello");
        flowRule.setCount(10);
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        flowRule.setLimitApp("default");
        FlowRuleManager.loadRules(Collections.singletonList(flowRule));

//        FlowRule flowRule = new FlowRule();
//        flowRule.setResource("com.lidong.dubbo.service.UserService:findAll()");
//        flowRule.setCount(5);
//        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
//        flowRule.setLimitApp("default");
//        FlowRuleManager.loadRules(Collections.singletonList(flowRule));
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(DubboSpringCloudConsumerBootstrap.class)
                .properties("spring.profiles.active=nacos").run(args);
        UserService userService = applicationContext.getBean(UserService.class);
        for (int i = 0; i < 15; i++) {
            try {
                Long id = Long.valueOf(i);
                String name = "name"+i;
                Integer age = i*2;
                userService.save(new User(id,name,age));
                System.out.println((i + 1) + " -> Success: 插入");
            }
            catch (SentinelRpcException ex) {
                System.out.println("Blocked");
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }




    @RestController
    public class TestController {

        /**
         * @SentinelResource 注解用来标识资源是否被限流、降级
         * @return
         */
        @GetMapping(value = "/hello")
        @SentinelResource("hello")
        public String hello() {
            return "Hello Sentinel";
        }

    }
}

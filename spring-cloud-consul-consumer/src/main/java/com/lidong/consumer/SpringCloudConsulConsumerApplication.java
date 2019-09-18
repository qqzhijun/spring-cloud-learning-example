package com.lidong.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class SpringCloudConsulConsumerApplication {



    @Autowired
    private RestTemplateBuilder builder;

    @LoadBalanced
    @Bean// 添加负载均衡支持，很简单，只需要在RestTemplate上添加@LoadBalanced注解，那么RestTemplate即具有负载均衡的功能,如果不加@LoadBalanced注解的话，会报java.net.UnknownHostException:springboot-h2异常，此时无法通过注册到Eureka Server上的服务名来调用服务，因为RestTemplate是无法从服务名映射到ip:port的，映射的功能是由LoadBalancerClient来实现的。
    public RestTemplate restTemplate() {
        return builder.build();
    }


    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConsulConsumerApplication.class, args);
    }


    @FeignClient(name = "service-producer")
    public interface EchoService {

        @RequestMapping(value = "/echo/{str}", method = RequestMethod.GET)
        String echo(@PathVariable("str") String str);

        @RequestMapping(value = "/divide", method = RequestMethod.GET)
        String divide(@RequestParam("a") Integer a, @RequestParam("b") Integer b);

        @RequestMapping(value = "/notFound", method = RequestMethod.GET)
        String notFound();
    }

}


class FeignConfiguration {
    @Bean
    public EchoServiceFallback echoServiceFallback() {
        return new EchoServiceFallback();
    }
}

class EchoServiceFallback implements SpringCloudConsulConsumerApplication.EchoService {
    @Override
    public String echo(@PathVariable("str") String str) {
        return "echo fallback";
    }

    @Override
    public String divide(@RequestParam Integer a, @RequestParam Integer b) {
        return "divide fallback";
    }

    @Override
    public String notFound() {
        return "notFound fallback";
    }
}


package com.lidong.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "spring-cloud-eureka-producer",
        fallback = HelloServiceFallback.class
//        url = "http://localhost:9011"
)
public interface HelloService {

    @GetMapping(value = "/hello")
    String callSayHello(@RequestParam("name") String name);

    @GetMapping(value = "/test")
    String test(@RequestParam("name") String name);
}

package com.lidong.consumer.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * 创建服务的消费者
 */
@RestController
public class ConsumerController {

    private static final String SERVICE_NAME = "spring-cloud-eureka-producer";

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 获取所有服务
     */
    @RequestMapping("/services")
    public Object services() {
        return discoveryClient.getInstances(SERVICE_NAME);
    }

    /**
     * 获取所有服务
     */
    @RequestMapping("/callSayHello")
    public String services(@RequestParam("name") String name) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("name",name);
        String callServiceResult = restTemplate.getForObject("http://"+SERVICE_NAME+"/hello?name={name}", String.class,paramMap);
        System.out.println(callServiceResult);
        return callServiceResult;
    }
}

package com.lidong.gateway.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * 创建服务
 */
@RestController
public class NacosProducerController {

    @Value("${server.port}")
    private Integer port;

    /**
     * 服务接口
     * @param name
     * @return
     */
    @RequestMapping("/hello")
    public String sayHello(@RequestParam("name")String name) {
        return "hello ---> "+name+" port -->"+port;
    }


}
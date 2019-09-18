package com.lidong.gateway.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * 创建服务
 */
@RestController
public class ConsulProducerController {



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

    /**
     * 服务接口
     * @param
     * @return
     */
    @RequestMapping("/hello2")
    public String sayHello2() {
        return "hello2 ---> ";
    }


    @RequestMapping(value = "/echo/{string}", method = RequestMethod.GET)
    public String echo(@PathVariable String string) {
        return "hello Nacos Discovery " + string;
    }

    @RequestMapping(value = "/divide", method = RequestMethod.GET)
    public String divide(@RequestParam Integer a, @RequestParam Integer b) {
        return String.valueOf(a / b);
    }
}
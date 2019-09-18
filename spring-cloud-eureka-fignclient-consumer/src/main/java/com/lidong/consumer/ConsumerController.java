package com.lidong.consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 创建服务的消费者
 */
@RestController
public class ConsumerController {

   @Autowired
   HelloService helloService;

    /**
     * 获取所有服务
     */
    @RequestMapping(value = "/callSayHello")
    public String services(@RequestParam(value = "name",required = false) String name) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        String callServiceResult = helloService.callSayHello(name);
        return callServiceResult;
    }

    @RequestMapping(value = "/test")
    public String test(@RequestParam(value = "name",required = false) String name) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        String callServiceResult = helloService.test(name);
        return callServiceResult;
    }
}

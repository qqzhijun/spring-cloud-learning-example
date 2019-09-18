package com.lidong.consumer;

import org.springframework.stereotype.Component;

@Component
public class HelloServiceFallback implements HelloService {

    @Override
    public String callSayHello(String name) {
        return "Error----"+name;
    }

    @Override
    public String test(String name) {
        return "没有资源";
    }

}

package com.lidong.bootstrap;

import com.lidong.dubbo.service.User;

import java.util.Map;

public class DubboFeignRestServiceFallback implements DubboFeignRestService{

    @Override
    public String param(String param) {
        return null;
    }

    @Override
    public String params(String paramB, int paramA) {
        return "echo fallback params";
    }

    @Override
    public User requestBody(String param, Map<String, Object> data) {
        return new User(0L,"requestBody",0);
    }

    @Override
    public String headers(String header2, Integer value, String header) {
        return "echo fallback headers";
    }

    @Override
    public String pathVariables(String param, String path2, String path1) {
        return "echo fallback pathVariables";
    }
}

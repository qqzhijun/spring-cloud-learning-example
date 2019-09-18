package com.lidong.webiot.strategy;

import com.lidong.webiot.strategy.impl.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 指令数据工厂模式
 */
public class RequestCommdTypeStrategyFactory {

    private static RequestCommdTypeStrategyFactory factory = new RequestCommdTypeStrategyFactory();

    private RequestCommdTypeStrategyFactory() {
    }

    private static Map<String,RequestCommdTypeStrategy> strategyMap = new HashMap<>();

    static {
        strategyMap.put(RequestCommdTypeEnum.REQUEST_COMMAND_TYPE_200.getValue(), new RequestCommandType200Strategy());
    }

    public RequestCommdTypeStrategy creator(String type) {
        return strategyMap.get(type);
    }

    public static RequestCommdTypeStrategyFactory getInstance() {
        return factory;
    }
}

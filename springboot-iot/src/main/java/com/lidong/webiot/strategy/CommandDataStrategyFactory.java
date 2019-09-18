package com.lidong.webiot.strategy;

import com.lidong.webiot.strategy.impl.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 指令数据工厂模式
 */
public class CommandDataStrategyFactory {

    private static CommandDataStrategyFactory factory = new CommandDataStrategyFactory();

    private CommandDataStrategyFactory() {
    }

    private static Map<String,CommandDataStrategy> strategyMap = new HashMap<>();

    static {
        strategyMap.put(CommandDataEnum.COMMAND_DATA_001.getValue(), new E001CommandDataStrategy());
    }

    public CommandDataStrategy creator(String type) {
        return strategyMap.get(type);
    }

    public static CommandDataStrategyFactory getInstance() {
        return factory;
    }
}

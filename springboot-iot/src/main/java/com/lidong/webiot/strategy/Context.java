package com.lidong.webiot.strategy;

import io.netty.channel.ChannelHandlerContext;

/**
 *
 */
public class Context {

    /**
     * 设备指令处理
     */
    private CommandDataStrategy strategy;
    /**
     * 请求数据处理
     */
    private RequestCommdTypeStrategy  requestCommdTypeStrategy;

    /**
     * 处理发送过来的数据
     * @param type
     * @param data
     * @return
     */
    public void calCommandData(String type, String data, ChannelHandlerContext ctx) {
       strategy = CommandDataStrategyFactory.getInstance().creator(type);
       strategy.handleCommandData(data,ctx);
    }

    public String handleRequestCommdType(String type,String iemi,String cmdCode,String params,String taskId,String createTime){
        requestCommdTypeStrategy = RequestCommdTypeStrategyFactory.getInstance().creator(type);
        return requestCommdTypeStrategy.handleRequestCommdType(iemi,cmdCode,params,taskId,createTime);
    }

    public CommandDataStrategy getStrategy() {
       return strategy;
    }

    public void setStrategy(CommandDataStrategy strategy) {
       this.strategy = strategy;
    }



    public RequestCommdTypeStrategy getRequestCommdTypeStrategy() {
        return requestCommdTypeStrategy;
    }

    public void setRequestCommdTypeStrategy(RequestCommdTypeStrategy strategy) {
        this.requestCommdTypeStrategy = strategy;
    }

}

package com.lidong.webiot.strategy;

import io.netty.channel.ChannelHandlerContext;

/**
  * @Description:指令策略接口
  * @time:2019年7月30日 上午10:03:27
 */
public interface CommandDataStrategy {

    /**
     * 处理上行的数据包
     * @param data
     * @param ctx
     */
    void handleCommandData(String data,ChannelHandlerContext ctx);
}


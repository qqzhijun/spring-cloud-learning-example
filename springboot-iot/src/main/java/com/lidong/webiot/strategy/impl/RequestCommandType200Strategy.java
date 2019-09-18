package com.lidong.webiot.strategy.impl;

import com.lidong.webiot.netty.ClientCache;
import com.lidong.webiot.strategy.RequestCommdTypeStrategy;
import com.lidong.webiot.utils.DateUtils;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;
import java.util.UUID;

/**
 *
 * 音量等级	200	0-11，最大值为 11
 *
 * [026]下行音量调整数据包
 *
 */
public class RequestCommandType200Strategy implements RequestCommdTypeStrategy {
    /**
     * @param iemi    设备imei
     * @param cmdCode 指令编码
     * @param params  指令参数 0-7
     * @return
     */
    @Override
    public String handleRequestCommdType(String iemi, String cmdCode, String params,String taskId,String createTime) {
        String returnData="@B#@|01|026|"+iemi+"|"+params+"|"+createTime+"|"+taskId+"|@E#@";
        return returnData;
    }
}

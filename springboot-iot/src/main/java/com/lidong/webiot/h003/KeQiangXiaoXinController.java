package com.lidong.webiot.h003;

import com.lidong.webiot.netty.ClientCache;
import com.lidong.webiot.strategy.Context;
import com.lidong.webiot.utils.DateUtils;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.C;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

git reset --head 3e5df36c631dba0d9a2a2ec0d7616767e8f210fe HEAD is now at d83e1ae
@Slf4j
@RestController
@RequestMapping("/command")
public class KeQiangXiaoXinController {


    /***
     * 014 向设备发送指令(下发指令)
     * [说明] 指令接口
     * 包含：
     * 200音量等级、
     * @param imei
     * @param cmdCode
     * @param params
     * @return
     */
    @RequestMapping("/sendCommand")
    public String sendCommand(@RequestParam(value = "imei",required = false) String imei,
                              @RequestParam(value = "cmdCode",required = false)String cmdCode,
                              @RequestParam(value = "params",required = false)String params) {
        imei = "xxxxxxxxxxxxxxxxxxxxxxxx";
        Context  context = new Context();
        String taskId = UUID.randomUUID().toString().replaceAll("-","");//任务id
        String createTime = DateUtils.dateToStringYyyyMMddHHmmss(new Date());
        String cmdData = context.handleRequestCommdType(cmdCode,imei,cmdCode,params,taskId,createTime);
        ClientCache.clientMap.get(imei).writeAndFlush(cmdData);
        return "success";
    }
}

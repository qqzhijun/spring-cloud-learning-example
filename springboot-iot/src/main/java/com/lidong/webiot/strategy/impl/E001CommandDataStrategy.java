package com.lidong.webiot.strategy.impl;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.lidong.webiot.strategy.CommandDataStrategy;
import com.lidong.webiot.utils.CoderUtils;
import com.lidong.webiot.utils.DateUtils;
import com.lidong.webiot.utils.GetAddressUtils;
import com.lidong.webiot.utils.SmsUtils;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

import static com.lidong.webiot.utils.SmsUtils.TEMPLATE_CODE_SYSTEM_NOTICE;

/**
 * GPS 数据上传场景
 */
@Slf4j
public class E001CommandDataStrategy implements CommandDataStrategy {

    @Override
    public void handleCommandData(String data, ChannelHandlerContext ctx) {
        if (StringUtils.isNotBlank(data)) {
            //gps位置数据 12
            //基站定位   14
            //wifi定位  15
            String[] split = data.split("\\|");
            String imei = split[3];
            String status = "0";
            String createTime = DateUtils.dateToStringYyyyMMddHHmmss(new Date());
            String[] arr = GetAddressUtils.getAddress(data);
            String ss = arr[0];
            log.info("位置信息:"+ss);
            String address = CoderUtils.stringToUnicode(ss);
            log.info("位置信息CODE:"+address);
            ss = ss.substring(0,18);
            try {
                SendSmsResponse systemNotice = SmsUtils.sendMsmSystemNotice(SmsUtils.ERROR_MOBILE, "位置服务", ss, TEMPLATE_CODE_SYSTEM_NOTICE, SmsUtils.SIGN_NAME_YYB);
                log.info("短信结果:"+JSON.toJSON(systemNotice));
            } catch (ClientException e) {
                e.printStackTrace();
            }
            String returnData = "@B#@|01|002|"+imei+"|"+status+"|" + address + "|" + createTime + "|@E#@";
            log.info("返回数据:"+returnData);
            ctx.channel().writeAndFlush(returnData);
        }
    }
}

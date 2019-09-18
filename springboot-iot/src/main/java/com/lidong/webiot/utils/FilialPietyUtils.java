package com.lidong.webiot.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * 孝心服务数据解析
 */
public class FilialPietyUtils {

    /**
     *
     * @param receiveData 收到的数据
     * @return
     */
    public static String parseData(String receiveData){
        String returnData;
        if (StringUtils.isNotBlank(receiveData) && receiveData.contains("|")){
             String[] split = receiveData.split("|");
             if (split!=null && split.length> 3){
                 String protocolVersion = split[1];//协议版本号
                 String commandData = split[2];//类别代码
//                 String imei = split[3];//IMEI 共 15 位字符，“111112222233333”
//                 String imsi = split[4];//IMSI 共 16 位字符，“1111122222333334”
//                 String status = split[5];//状态 终端佩戴状态（0:未佩戴 1:已佩戴）
//                 String power = split[6];//电量 剩余电量百分比（range:1-100）
//                 String dataTime = split[6];//终端当前时间(年月日时分秒)
//                 String dataTime = split[6];//终端当前时间(年月日时分秒)
             }
        }
        return receiveData;
    }
}

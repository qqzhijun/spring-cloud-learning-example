package com.lidong.webiot.h003;

/**
 * 请求
 */
public interface RequestCommdType {

    int REQUEST_COMMAND_TYPE_200 = 200;// 音量等级、
    int REQUEST_COMMAND_TYPE_201 = 201;//心率上传间隔、
    int REQUEST_COMMAND_TYPE_202 = 202;//GPS静默时间段
    int REQUEST_COMMAND_TYPE_203 = 203;//GPS开关、
    int REQUEST_COMMAND_TYPE_204 = 204;//GPS上传间隔
    int REQUEST_COMMAND_TYPE_205 = 205;//低电提醒时间段
    int REQUEST_COMMAND_TYPE_206 = 206;//白名单开关
    int REQUEST_COMMAND_TYPE_207 = 207;//白名单
    int REQUEST_COMMAND_TYPE_208 = 208;//黄键短信开关
    int REQUEST_COMMAND_TYPE_209 = 209;//红键短信开关
    int REQUEST_COMMAND_TYPE_210 = 210;//时区默认值
    int REQUEST_COMMAND_TYPE_211 = 211;//闹钟
    int REQUEST_COMMAND_TYPE_212 = 212;//亲情号码
}

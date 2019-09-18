package com.lidong.webiot.strategy;

/**
 * 请求
 */
public enum RequestCommdTypeEnum {

    REQUEST_COMMAND_TYPE_200("200"),// 音量等级、
    REQUEST_COMMAND_TYPE_201("201"),//心率上传间隔、
    REQUEST_COMMAND_TYPE_202("202"),//GPS静默时间段
    REQUEST_COMMAND_TYPE_203("203"),//GPS开关、
    REQUEST_COMMAND_TYPE_204("204"),//GPS上传间隔
    REQUEST_COMMAND_TYPE_205("205"),//低电提醒时间段
    REQUEST_COMMAND_TYPE_206("206"),//白名单开关
    REQUEST_COMMAND_TYPE_207("207"),//白名单
    REQUEST_COMMAND_TYPE_208("208"),//黄键短信开关
    REQUEST_COMMAND_TYPE_209("209"),//红键短信开关
    REQUEST_COMMAND_TYPE_210("210"),//时区默认值
    REQUEST_COMMAND_TYPE_211("211"),//闹钟
    REQUEST_COMMAND_TYPE_212("212"),//亲情号码
    REQUEST_COMMAND_TYPE_213("213"),//心率报警上下限设置包
    REQUEST_COMMAND_TYPE_214("214"),//下行解绑设置包
    REQUEST_COMMAND_TYPE_215("215"),//检测健康数据包
    REQUEST_COMMAND_TYPE_216("216");//下行远程关机包

    RequestCommdTypeEnum(String value) {
        this.setValue(value);
    }

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

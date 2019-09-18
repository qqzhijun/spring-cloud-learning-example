package com.lidong.webiot.strategy;

/**
 * 指令枚举类
 */
public enum CommandDataEnum {

    COMMAND_DATA_001("001"),//[001]上行当前位置数据包
    COMMAND_DATA_002("002"),//[002]下行确认位置数据包
    COMMAND_DATA_003("003"),//[003]上行开机数据包
    COMMAND_DATA_004("004"),//[004]下行开机确认数据包
    COMMAND_DATA_005("005"),//[005]上行关机数据包
    COMMAND_DATA_006("006"),//[006]上行告警数据包
    COMMAND_DATA_007("007"),//[007]下行按键告警确认数据包
    COMMAND_DATA_008("008"),//[008]下行亲属号码设置数据包
    COMMAND_DATA_009("009"),//[009]上行亲属号码设置确认数据包
    COMMAND_DATA_010("010"),//[010]上行血压数据包
    COMMAND_DATA_011("011"),//[011]下行血压确认数据包
    COMMAND_DATA_012("012"),//[012]上行心率/步数/睡眠数据数据包
    COMMAND_DATA_013("013"),//[013]下行心率/步数/睡眠数据确认数据包
    COMMAND_DATA_014("014"),//[014]下行心率|GPS 数据周期设置数据包
    COMMAND_DATA_015("015"),//[015]上行心率|GPS 数据周期设置确认数据包
    COMMAND_DATA_016("016"),//[016] 下行 GPS 静默|低电量提醒|测量心率血压时间段设置数据包
    COMMAND_DATA_017("017"),//[017]上行 GPS 静默|低电量提醒|测量心率血压时间段设置确认数据包
    COMMAND_DATA_018("018"),//[018]下行逻辑开关配置开关数据包
    COMMAND_DATA_019("019"),//[019]上行逻辑开关配置确认数据包
    COMMAND_DATA_020("020"),//[020]下行设置白名单数据数据包
    COMMAND_DATA_021("021"),//[021]上行设置白名单数据确认数据包
    COMMAND_DATA_022("022"),
    COMMAND_DATA_023("023"),
    COMMAND_DATA_024("024"),
    COMMAND_DATA_025("025"),
    COMMAND_DATA_026("026"),//[026]下行音量调整数据包
    COMMAND_DATA_027("027"),//[027]上行音量调整确认数据包
    COMMAND_DATA_028("028"),//[028]上行低电量告警数据包
    COMMAND_DATA_029("029"),////[029]上行心率异常告警数据包
    COMMAND_DATA_030("030"),//[030]下行心率/行为异常告警确认数据包
    COMMAND_DATA_031("031"),//
    COMMAND_DATA_032("032"),//
    COMMAND_DATA_033("033"),//[033]上行终端数据包
    COMMAND_DATA_034("034"),//[034]下行终端确认数据包
    COMMAND_DATA_035("035"),
    COMMAND_DATA_036("036"),
    COMMAND_DATA_037("037"),
    COMMAND_DATA_038("038"),
    COMMAND_DATA_039("039"),//17.1.[039]上行短信触发请求数据包
    COMMAND_DATA_040("040"),
    COMMAND_DATA_041("041"),
    COMMAND_DATA_042("042"),//[042]下行服务中心配置数据组包
    COMMAND_DATA_043("043"),//[043]上行服务中心配置数据确认组包
    COMMAND_DATA_044("044"),//[044]下行语音文件配置包
    COMMAND_DATA_045("045"),//[045]上行语音文件配置确认包
    COMMAND_DATA_046("046"),//[046]下行闹钟配置包
    COMMAND_DATA_047("047"),//[047]上行闹钟配置确认包
    COMMAND_DATA_048("048"),//[048]下行定位请求包
    COMMAND_DATA_049("049"),//[049]下行时区设置包
    COMMAND_DATA_050("050"),//[050]上行时区设置确认包
    COMMAND_DATA_051("051"),//[051]下行远程关机包
    COMMAND_DATA_052("052"),//[052]上行远程关机确认包
    COMMAND_DATA_053("053"),//[053]下行心率报警上下限设置包
    COMMAND_DATA_054("054"),//[054]上行心率报警上下限确认包
    COMMAND_DATA_055("055"),//[055]下行解绑设置包
    COMMAND_DATA_056("056"),//[056]上行解绑设置确认包
    COMMAND_DATA_057("057"),//[057]下行检测健康数据包
    COMMAND_DATA_058("058"),//[058]上行检测健康数据确认包
    COMMAND_DATA_059("059"),//[059]上行天气请求数据包
    COMMAND_DATA_060("060"),//[060]下行天气数据包
    ;//[027]上行音量调整确认数据包

    CommandDataEnum(String value) {
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

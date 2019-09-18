package com.lidong.webiot.strategy;


/**
 * 请求数据处理
 */
public interface RequestCommdTypeStrategy {

    /**
     *
     * @param iemi 设备imei
     * @param cmdCode 指令编码
     * @param params  指令参数
     * @return
     */
    String  handleRequestCommdType(String iemi,String cmdCode,String params,String taskId,String createTime);
}

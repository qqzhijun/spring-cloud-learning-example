package com.lidong.service;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;

/**
 * @author: ChenJie
 * @date 2018/8/21
 */
@MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
public interface MqttGateway {

    void sendToMqtt(String data, @Header(MqttHeaders.TOPIC) String topic);

    void sendToMqtt(String data);
}

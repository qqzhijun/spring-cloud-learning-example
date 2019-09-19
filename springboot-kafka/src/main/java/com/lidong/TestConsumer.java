package com.lidong;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * kafka消费者测试
 */
@Component
@Slf4j
public class TestConsumer {

    @KafkaListener(topics = "test_topic")
    public void listen (ConsumerRecord<?, ?> record) throws Exception {
        log.info("分区信息",record.partition());
        log.info("topic = %s, offset = %d, value = %s \n", record.topic(), record.offset(), record.value());
    }
}

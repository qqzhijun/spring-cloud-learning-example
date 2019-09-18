package com.lidong.flink.mysql;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQProducerUtil {


    public final static String QUEUE_NAME = "zhisheng";

    public static void main(String[] args) throws Exception {
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();

        //设置RabbitMQ相关信息
        factory.setHost("192.168.10.200");
        factory.setUsername("worker");
        factory.setPassword("123456");
        factory.setVirtualHost("/");
        factory.setPort(5672);

        //创建一个新的连接
        Connection connection = factory.newConnection();

        //创建一个通道
        Channel channel = connection.createChannel();

        // 声明一个队列
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        //发送消息到队列中


        //我们这里演示发送一千条数据
        for (int i = 9500; i < 10000; i++) {

            String message = "{\"name\":\"name"+i+"\",\"age\":"+i+",\"account\":"+i+0.02+"}";
            channel.basicPublish("", QUEUE_NAME, null, (message).getBytes("UTF-8"));
            System.out.println("Producer Send +'" + message + i);
            Thread.sleep(2000);
        }

        //关闭通道和连接
        channel.close();
        connection.close();
    }
}

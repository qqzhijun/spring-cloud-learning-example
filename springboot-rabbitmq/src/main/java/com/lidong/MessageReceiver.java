package com.lidong;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import com.rabbitmq.client.Channel;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.amqp.core.Message;
@Component
public class MessageReceiver {

  @RabbitListener(queues = "test_queue_1",containerFactory = "myContainerFactory")
  public void receive(Message message , Channel channel) throws Exception {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    System.out.println("消息接收时间:"+sdf.format(new Date()));
    byte[] body = message.getBody();
    String sb = new String(body,"UTF-8");
    System.out.println("接收到的消息:"+ sb);
    System.out.println(channel);
    System.out.println("[延时消息]" + message.getMessageProperties());
    if (message != null) {
      long deliveryTag = message.getMessageProperties().getDeliveryTag();
      System.out.println(("deliveryTag= "+deliveryTag));
      //手动确认
      channel.basicAck(deliveryTag, false); // 消息的标识，false只确认当前一个消息收到，true确认所有consumer获得的消息
//      channel.basicNack(deliveryTag, false, true); // ack返回false，并重新回到队列，api里面解释得很清楚
      //hannel.basicReject(deliveryTag, true); // 拒绝消息
    }
  }
}
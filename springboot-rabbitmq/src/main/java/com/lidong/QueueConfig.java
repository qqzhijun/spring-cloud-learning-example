package com.lidong;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class QueueConfig {

  /**
   * 使用的是 CustomExchange ,不是 DirectExchange ，
   * 另外 CustomExchange 的类型必须是 x-delayed-message 。
   * @return
   */
  @Bean
  public CustomExchange delayExchange() {
    Map<String, Object> args = new HashMap<>();
    args.put("x-delayed-type", "direct");
    return new CustomExchange("test_exchange", "x-delayed-message",true, false,args);
  }

  /**
   * 声明一个死信队列.
   * @return
   */
  @Bean
  public Queue queue() {
    /**
     * name 队列名
     * durable true 队列声明为持久化
     */
    Queue queue = new Queue("test_queue_1", true);
    return queue;
  }

  /**
   * 将队列绑定到路由交换机
   * @return
   */
  @Bean
  public Binding binding() {
    return BindingBuilder.bind(queue()).to(delayExchange()).with("test_route_key_1").noargs();
  }


}

package com.lidong;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

  @Value("${spring.rabbitmq.host}")
  public String host;

  @Value("${spring.rabbitmq.port}")
  public int port;

  @Value("${spring.rabbitmq.username}")
  public String username;

  @Value("${spring.rabbitmq.password}")
  public String password;

  @Value("${spring.rabbitmq.virtual-host}")
  public String virtualHost;

  @Bean
  public ConnectionFactory connectionFactory() {
    CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(host,port);
    cachingConnectionFactory.setUsername(username);
    cachingConnectionFactory.setPassword(password);
    cachingConnectionFactory.setVirtualHost(virtualHost);
    cachingConnectionFactory.setPublisherConfirms(true);
    return cachingConnectionFactory;
  }

  @Bean
  public RabbitTemplate rabbitTemplate() {
    RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
    return rabbitTemplate;
  }

  @Bean("myContainerFactory")
  public SimpleRabbitListenerContainerFactory myContainerFactory(
          SimpleRabbitListenerContainerFactoryConfigurer configurer) {
    SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
    //开启手动确认消息
    factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
    factory.setPrefetchCount(100);
    configurer.configure(factory, connectionFactory());
    return factory;
  }


  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getVirtualHost() {
    return virtualHost;
  }

  public void setVirtualHost(String virtualHost) {
    this.virtualHost = virtualHost;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}

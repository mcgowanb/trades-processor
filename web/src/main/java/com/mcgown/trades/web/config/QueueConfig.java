package com.mcgown.trades.web.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import com.mcgowan.trades.producer.EnableProducer;
import com.mcgowan.trades.producer.QueueProducer;

@EnableProducer
public class QueueConfig {

  @Value("${spring.rabbitmq.host}")
  private String host;

  @Value("${spring.rabbitmq.username}")
  private String userName;

  @Value("${spring.rabbitmq.password}")
  private String password;

  @Value("${spring.rabbitmq.port}")
  private Integer port;

  @Bean
  public ConnectionFactory connectionFactory(){
    final CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
    connectionFactory.setUsername(userName);
    connectionFactory.setPassword(password);
    connectionFactory.setPort(port);
    return connectionFactory;
  }

  @Bean
  public QueueProducer producer(ConnectionFactory connectionFactory){
    return new QueueProducer(connectionFactory);
  }
}

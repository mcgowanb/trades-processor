package com.mcgowan.trades.consumer.config;

import static com.mcgowan.trades.common.constants.Constants.AUTO_DELETE;
import static com.mcgowan.trades.common.constants.Constants.DEAD_LETTER_EXCHANGE;
import static com.mcgowan.trades.common.constants.Constants.DEAD_LETTER_QUEUE;
import static com.mcgowan.trades.common.constants.Constants.DEAD_LETTER_ROUTING_KEY;
import static com.mcgowan.trades.common.constants.Constants.EXCHANGE_NAME;
import static com.mcgowan.trades.common.constants.Constants.IS_DURABLE;
import static com.mcgowan.trades.common.constants.Constants.IS_EXCLUSIVE;
import static com.mcgowan.trades.common.constants.Constants.QUEUE;
import static com.mcgowan.trades.common.constants.Constants.RETRY_EXCHANGE;
import static com.mcgowan.trades.common.constants.Constants.RETRY_QUEUE_NAME;
import static com.mcgowan.trades.common.constants.Constants.RETRY_ROUTING_KEY;
import static com.mcgowan.trades.common.constants.Constants.ROUTING_KEY;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.isomorphism.util.TokenBucket;
import org.isomorphism.util.TokenBuckets;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

import com.mcgowan.trades.common.constants.Constants;

@Configuration
@EnableRabbit
public class ConsumerQueueConfig implements RabbitListenerConfigurer {

  @Bean
  public Queue queue() {
    return new Queue(QUEUE, IS_DURABLE, IS_EXCLUSIVE, AUTO_DELETE);
  }

  @Bean
  public Queue retryQueue() {
    return new Queue(RETRY_QUEUE_NAME, IS_DURABLE, IS_EXCLUSIVE, AUTO_DELETE);
  }

  @Bean
  public Queue deadLetterQueue() {
    return new Queue(DEAD_LETTER_QUEUE, IS_DURABLE, IS_EXCLUSIVE, AUTO_DELETE);
  }

  @Bean
  public DirectExchange exchange() {
    return new DirectExchange(EXCHANGE_NAME);
  }

  @Bean
  public CustomExchange delayExchange() {
    Map<String, Object> args = new HashMap<>();
    args.put("x-delayed-type", "direct");
    return new CustomExchange(Constants.RETRY_EXCHANGE, "x-delayed-message", IS_DURABLE, AUTO_DELETE, args);
  }

  @Bean
  public DirectExchange deadLetterExchange() {
    return new DirectExchange(DEAD_LETTER_EXCHANGE);
  }

  @Bean
  public Binding binding(Queue queue, DirectExchange exchange) {
    return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
  }

  @Bean
  public Binding bindingRetryQueue(final Queue retryQueue, final CustomExchange delayExchange) {
    return BindingBuilder.bind(retryQueue).to(delayExchange).with(RETRY_ROUTING_KEY).noargs();
  }

  @Bean
  public Binding bindingDeadLetterQueue(Queue deadLetterQueue, DirectExchange deadLetterExchange) {
    return BindingBuilder.bind(deadLetterQueue).to(deadLetterExchange).with(DEAD_LETTER_ROUTING_KEY);
  }

  @Bean
  public MappingJackson2MessageConverter consumerJacksonMessageConverter() {
    return new MappingJackson2MessageConverter();
  }

  @Bean
  public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory() {
    DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
    factory.setMessageConverter(consumerJacksonMessageConverter());
    return factory;
  }

  @Override
  public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
    registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
  }

  @Bean
  public TokenBucket tokenBucket() {
    return TokenBuckets.builder().withCapacity(1).withFixedIntervalRefillStrategy(1, 1, TimeUnit.SECONDS).build();
  }
}

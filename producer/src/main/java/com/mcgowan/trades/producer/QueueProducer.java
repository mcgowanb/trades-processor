package com.mcgowan.trades.producer;

import static com.mcgowan.trades.common.constants.Constants.EXCHANGE_NAME;
import static com.mcgowan.trades.common.constants.Constants.ROUTING_KEY;

import javax.inject.Inject;
import javax.inject.Named;

import lombok.extern.log4j.Log4j2;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import com.mcgowan.trades.common.DTO.TradeDTO;
import com.rabbitmq.client.ConnectionFactory;

@Log4j2
@Named
public class QueueProducer {

  private final RabbitTemplate rabbitTemplate;

  private final ConnectionFactory connectionFactory;

  @Inject
  public QueueProducer(RabbitTemplate rabbitTemplate, ConnectionFactory connectionFactory) {
    this.rabbitTemplate = rabbitTemplate;
    this.connectionFactory = connectionFactory;
  }

  public void sendMessage(final TradeDTO trade){
    log.info("Sending trade {}", trade);
    try{
      rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, trade);
      log.info("Trade sent {}", trade);
    }
    catch (final AmqpException e){
      log.error("sending trade failed with error: {}\nTrade {}", e, trade);
    }


  }
}

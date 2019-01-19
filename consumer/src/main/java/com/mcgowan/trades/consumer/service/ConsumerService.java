package com.mcgowan.trades.consumer.service;

import javax.inject.Inject;
import javax.inject.Named;

import lombok.extern.log4j.Log4j2;

import org.isomorphism.util.TokenBucket;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import com.mcgowan.trades.common.DTO.TradeDTO;
import com.mcgowan.trades.common.constants.Constants;

@Named
@Log4j2
public class ConsumerService {

  private final RabbitTemplate rabbitTemplate;

  private final TokenBucket tokenBucket;

  @Inject
  public ConsumerService(RabbitTemplate rabbitTemplate, TokenBucket tokenBucket) {
    this.rabbitTemplate = rabbitTemplate;
    this.tokenBucket = tokenBucket;
  }

  @RabbitListener(queues = Constants.QUEUE)
  public void receiveTrade(final TradeDTO trade) {
    try {
      log.info("Following message recieved: {}", trade);
      //auto ACK on message
      //throw exception to retry message
      //throw new Exception()
    }
    catch (Exception e) {
      rabbitTemplate.convertAndSend(Constants.RETRY_QUEUE_NAME, Constants.RETRY_EXCHANGE, trade, failedMessage -> {
        failedMessage.getMessageProperties().setHeader("x-delay", Constants.RETRY_DELAY);
        return failedMessage;
      });
    }
  }
}

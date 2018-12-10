package com.mcgowan.trades.consumer.service;

import javax.inject.Named;

import lombok.extern.log4j.Log4j2;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import com.mcgowan.trades.common.DTO.TradeDTO;
import com.mcgowan.trades.common.constants.Constants;

@Named
@Log4j2
public class RetryListenerService {

  private final RabbitTemplate rabbitTemplate;

  public RetryListenerService(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  @RabbitListener(queues = Constants.RETRY_QUEUE_NAME)
  public void retryTradeHandler(final TradeDTO failedTrade){
    int retryCount = (Integer) failedTrade.getProperties().get(Constants.RETRY_HEADER_COUNT);
    if(retryCount < Constants.MAX_RETRIES){
      failedTrade.getProperties().put(Constants.RETRY_HEADER_COUNT, ++retryCount);
      log.info("Retry count for trade {}", retryCount);
      rabbitTemplate.convertAndSend(Constants.EXCHANGE_NAME, Constants.ROUTING_KEY, failedTrade);
    }
    else{
      log.info("Retries exceeded, pushing the following trade to dlq {}", failedTrade);
      rabbitTemplate.convertAndSend(Constants.DEAD_LETTER_EXCHANGE, Constants.DEAD_LETTER_ROUTING_KEY, failedTrade);
    }
  }

}

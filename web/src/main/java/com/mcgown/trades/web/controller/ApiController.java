package com.mcgown.trades.web.controller;

import javax.inject.Inject;

import lombok.extern.log4j.Log4j2;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mcgowan.trades.common.DTO.TradeDTO;
import com.mcgowan.trades.producer.QueueProducer;

@RestController
@Log4j2
@RequestMapping("api")
public class ApiController {

  private final QueueProducer queueProducer;

  @Inject
  public ApiController(QueueProducer queueProducer) {
    this.queueProducer = queueProducer;
  }

  @PostMapping("/trade")
  public HttpStatus processTrade(@RequestBody final TradeDTO tradeDTO) {
    tradeDTO.validate();
    log.info("Recieved trade via controller {}", tradeDTO);
    queueProducer.sendMessage(tradeDTO);
    return HttpStatus.OK;
  }
}

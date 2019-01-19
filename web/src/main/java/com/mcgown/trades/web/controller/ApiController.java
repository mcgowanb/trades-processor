package com.mcgown.trades.web.controller;

import javax.inject.Inject;

import io.reactivex.subjects.PublishSubject;
import lombok.extern.log4j.Log4j2;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
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

  private final PublishSubject<TradeDTO> observer;

  @Inject
  public ApiController(QueueProducer queueProducer) {
    this.queueProducer = queueProducer;
    observer = PublishSubject.create();
  }

  @PostMapping("/trade")
  public HttpStatus processTrade(@RequestBody final TradeDTO tradeDTO) {
    tradeDTO.validate();
    log.info("Received trade via controller {}", tradeDTO);
    queueProducer.sendMessage(tradeDTO);
    observer.onNext(tradeDTO);
    return HttpStatus.OK;
  }

  @GetMapping(value = "live-trades", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public PublishSubject<TradeDTO> liveTrades() {
    return observer;
  }
}

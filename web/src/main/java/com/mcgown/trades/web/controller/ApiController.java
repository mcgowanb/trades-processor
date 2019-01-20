package com.mcgown.trades.web.controller;

import java.time.Duration;

import javax.inject.Inject;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mcgowan.trades.common.DTO.TradeDTO;
import com.mcgowan.trades.producer.QueueProducer;
import com.mcgown.trades.web.repository.TradeRepository;

@RestController
@Log4j2
@RequestMapping("api")
public class ApiController {

  private final QueueProducer queueProducer;

  private final TradeRepository tradeRepository;

  private static final int DELAY_PER_ITEM_MS = 100;

  @Inject
  public ApiController(QueueProducer queueProducer, TradeRepository tradeRepository) {
    this.queueProducer = queueProducer;
    this.tradeRepository = tradeRepository;
  }

  @PostMapping("/trade")
  public HttpStatus processTrade(@RequestBody final TradeDTO tradeDTO) {
    tradeDTO.validate();
    log.info("Received trade via controller {}", tradeDTO);
    queueProducer.sendMessage(tradeDTO);
    return HttpStatus.OK;
  }

  //  @GetMapping(value = "live-trades", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  //  public PublishSubject<TradeDTO> liveTrades() {
  //    return observer;
  //  }

  @GetMapping(value = "/trades-reactive", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<TradeDTO> getQuoteFlux() {
    // If you want to use a shorter version of the Flux, use take(100) at the end of the statement below
    log.info("trades-reactive endpoint hit");
    return tradeRepository.findAll().map(TradeDTO::tradeToDto);
  }

  @GetMapping("/trades-reactive-paged")
  public Flux<TradeDTO> getQuoteFlux(final @RequestParam(name = "page") int page,
      final @RequestParam(name = "size") int size) {
    return tradeRepository.retrieveAllTradesPaged(PageRequest.of(page, size))
        .delayElements(Duration.ofMillis(DELAY_PER_ITEM_MS))
        .map(TradeDTO::tradeToDto);
  }
}

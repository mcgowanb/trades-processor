package com.mcgown.trades.web.repository;

import reactor.core.publisher.Flux;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.mcgowan.trades.common.entity.Trade;

public interface TradeRepository extends ReactiveCrudRepository<Trade, String> {

  @Query("{ id: { $exists: true }}")
  Flux<Trade> retrieveAllTradesPaged(final Pageable page);
}

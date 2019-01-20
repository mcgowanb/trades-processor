package com.mcgown.trades.web.repository;

import javax.inject.Named;

import reactor.core.publisher.Flux;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.mcgowan.trades.common.entity.TradeEntity;

public interface TradeRepository extends ReactiveCrudRepository<TradeEntity, String> {

  @Query("{ id: { $exists: true }}")
  Flux<TradeEntity> retrieveAllTradesPaged(final Pageable page);
}

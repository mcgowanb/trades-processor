package com.mcgowan.trades.common.repository;

import com.mcgowan.trades.common.DTO.TradeDTO;
import com.mcgowan.trades.common.entity.TradeEntity;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import reactor.core.publisher.Flux;

public interface TradeRepository extends ReactiveCrudRepository<TradeEntity, String> {
  
  @Query("{ id: { $exists: true }}")
  Flux<TradeEntity> retrieveAllTradesPaged(final Pageable page);
}

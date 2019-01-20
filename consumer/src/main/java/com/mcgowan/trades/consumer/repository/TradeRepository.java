package com.mcgowan.trades.consumer.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.mcgowan.trades.common.entity.TradeEntity;

public interface TradeRepository extends ReactiveCrudRepository<TradeEntity, String> {


}

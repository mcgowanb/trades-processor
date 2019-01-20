package com.mcgowan.trades.consumer.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.mcgowan.trades.common.entity.Trade;

public interface TradeRepository extends ReactiveCrudRepository<Trade, String> {
}

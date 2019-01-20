package com.mcgowan.trades.consumer.repository;

import com.mcgowan.trades.common.entity.Trade;

public interface TradeRepository extends ReactiveCrudRepository<Trade, String> {
}

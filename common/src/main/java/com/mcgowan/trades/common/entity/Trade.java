package com.mcgowan.trades.common.entity;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.mcgowan.trades.common.DTO.TradeDTO;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Trade {

  private String id;

  private Long userId;

  private Currency currencyFrom;

  private Currency currencyTo;

  private BigDecimal amountSell;

  private BigDecimal amountBuy;

  private BigDecimal rate;

  private Date timePlaced;

  private String originatingCountry;

  public static Trade dtoToTrade(final TradeDTO tradeDTO) {
    return Trade.builder()
        .userId(tradeDTO.getUserId())
        .currencyFrom(tradeDTO.getCurrencyFrom())
        .currencyTo(tradeDTO.getCurrencyTo())
        .amountBuy(tradeDTO.getAmountBuy())
        .amountSell(tradeDTO.getAmountSell())
        .rate(tradeDTO.getRate())
        .timePlaced(tradeDTO.getTimePlaced())
        .originatingCountry(tradeDTO.getOriginatingCountry().toString())
        .build();
  }
}
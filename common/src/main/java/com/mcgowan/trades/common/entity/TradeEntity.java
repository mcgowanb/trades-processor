package com.mcgowan.trades.common.entity;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class TradeEntity {

  private String id;

  private Long userId;

  private Currency currencyFrom;

  private Currency currencyTo;

  private BigDecimal amountSell;

  private BigDecimal amountBuy;

  private BigDecimal rate;

  private Date timePlaced;

  private String originatingCountry;
}

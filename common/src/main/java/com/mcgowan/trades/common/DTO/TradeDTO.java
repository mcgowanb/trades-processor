package com.mcgowan.trades.common;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import com.neovisionaries.i18n.CountryCode;

@Builder
@Getter
@ToString
public class TradeDTO implements Serializable {

  private final Long userId;

  private final String currencyFrom;

  private final String currencyTo;

  private final BigDecimal amountSell;

  private final BigDecimal amountBuy;

  private final BigDecimal rate;

  private final LocalDateTime timePlaced;

  private final CountryCode originatingCountry;

}

package com.mcgowan.trades.web;

import java.time.LocalDateTime;

import com.mcgowan.trades.common.DTO.TradeDTO;

public class TestUtilities {

  public static TradeDTO tradeDTO() {
    return TradeDTO.builder().build();
  }

  public static int createRandomIntBetween(int start, int end) {
    return start + (int) Math.round(Math.random() * (end - start));
  }

  public static LocalDateTime randomDate() {
    int day = createRandomIntBetween(1, 28);
    int month = createRandomIntBetween(1, 12);
    int year = createRandomIntBetween(2017, 2018);
    return LocalDateTime.of(year, month, day, 9, 00);
  }
}

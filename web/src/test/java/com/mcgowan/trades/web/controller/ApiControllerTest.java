package com.mcgowan.trades.web.controller;

import static com.mcgowan.trades.common.DTO.com.mcgowan.trades.common.utilities.Utilities.randomBigDecimal;
import static com.mcgowan.trades.common.DTO.com.mcgowan.trades.common.utilities.Utilities.randomLong;
import static com.mcgowan.trades.web.TestUtilities.randomDate;
import static junit.framework.TestCase.assertTrue;

import java.math.BigDecimal;
import java.util.Currency;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import com.mcgowan.trades.common.DTO.TradeDTO;
import com.neovisionaries.i18n.CountryCode;

@RunWith(MockitoJUnitRunner.class)
public class ApiControllerTest {

  private ApiController controller;

  @Before
  public void setUp() throws Exception {
    controller = new ApiController();
  }

  @Test
  public void process_trade_returns_200() {
    HttpStatus result = controller.processTrade(Mockito.mock(TradeDTO.class));
    assertTrue(result.is2xxSuccessful());
  }

  @Test(expected = IllegalArgumentException.class)
  public void process_trade_amount_buy_is_less_than_0_throws_exception() {
    TradeDTO dto = TradeDTO.builder()
        .userId(randomLong())
        .amountBuy(BigDecimal.valueOf(-1))
        .amountSell(randomBigDecimal())
        .currencyFrom(Currency.getInstance("EUR"))
        .currencyTo(Currency.getInstance("GBP"))
        .originatingCountry(CountryCode.IE)
        .rate(randomBigDecimal())
        .timePlaced(randomDate())
        .build();
    controller.processTrade(dto);
  }

  @Test(expected = IllegalArgumentException.class)
  public void process_trade_amount_sell_is_less_than_0_throws_exception() {
    TradeDTO dto = TradeDTO.builder()
        .userId(randomLong())
        .amountBuy(randomBigDecimal())
        .amountSell(BigDecimal.valueOf(-1))
        .currencyFrom(Currency.getInstance("EUR"))
        .currencyTo(Currency.getInstance("GBP"))
        .originatingCountry(CountryCode.IE)
        .rate(randomBigDecimal())
        .timePlaced(randomDate())
        .build();
    controller.processTrade(dto);
  }

  @Test(expected = IllegalArgumentException.class)
  public void process_trade_rate_is_less_than_0_throws_exception() {
    TradeDTO dto = TradeDTO.builder()
        .userId(randomLong())
        .amountBuy(randomBigDecimal())
        .amountSell(randomBigDecimal())
        .currencyFrom(Currency.getInstance("EUR"))
        .currencyTo(Currency.getInstance("GBP"))
        .originatingCountry(CountryCode.IE)
        .rate(BigDecimal.valueOf(-1))
        .timePlaced(randomDate())
        .build();
    controller.processTrade(dto);
  }

  @Test(expected = IllegalArgumentException.class)
  public void process_trade_currency_from_is_null_throws_exception() {
    TradeDTO dto = TradeDTO.builder()
        .userId(randomLong())
        .amountBuy(randomBigDecimal())
        .amountSell(randomBigDecimal())
        .currencyTo(Currency.getInstance("GBP"))
        .originatingCountry(CountryCode.IE)
        .rate(randomBigDecimal())
        .timePlaced(randomDate())
        .build();
    controller.processTrade(dto);
  }

  @Test(expected = IllegalArgumentException.class)
  public void process_trade_currency_to_is_null_throws_exception() {
    TradeDTO dto = TradeDTO.builder()
        .userId(randomLong())
        .amountBuy(randomBigDecimal())
        .amountSell(randomBigDecimal())
        .currencyFrom(Currency.getInstance("GBP"))
        .originatingCountry(CountryCode.IE)
        .rate(randomBigDecimal())
        .timePlaced(randomDate())
        .build();
    controller.processTrade(dto);
  }
}
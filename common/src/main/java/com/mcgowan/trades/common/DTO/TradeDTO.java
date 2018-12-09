package com.mcgowan.trades.common.DTO;

import static java.util.Objects.nonNull;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.google.common.base.Preconditions;
import com.neovisionaries.i18n.CountryCode;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TradeDTO implements Serializable {

  @NotNull
  private Long userId;

  @NotNull
  private Currency currencyFrom;

  @NotNull
  private Currency currencyTo;

  @NotNull
  private BigDecimal amountSell;

  @NotNull
  private BigDecimal amountBuy;

  @NotNull
  private BigDecimal rate;

  @NotNull
  private LocalDateTime timePlaced;

  @NotNull
  private CountryCode originatingCountry;

  public void validate() {
    Preconditions.checkArgument(nonNull(userId), "User id is required");
    Preconditions.checkArgument(nonNull(currencyFrom), "You must provide a selling currency type");
    Preconditions.checkArgument(nonNull(currencyTo), "You must provide a buying currency type");
    Preconditions.checkArgument(amountBuy.compareTo(BigDecimal.ZERO) > 0, "Buying amount must be greater than 0");
    Preconditions.checkArgument(amountSell.compareTo(BigDecimal.ZERO) > 0, "Selling amount must be greater than 0");
    Preconditions.checkArgument(rate.compareTo(BigDecimal.ZERO) > 0, "Rate must be greater than 0");
  }

}

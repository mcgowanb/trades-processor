package com.mcgowan.trades.common.DTO;

import static java.util.Objects.nonNull;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.base.Preconditions;
import com.mcgowan.trades.common.constants.Constants;
import com.neovisionaries.i18n.CountryCode;

@Builder
@Getter
@ToString(exclude = "properties")
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
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
  private Date timePlaced;

  @NotNull
  private CountryCode originatingCountry;

  @Builder.Default
  private Map<String, Object> properties = new HashMap<String, Object>() {{
    put(Constants.RETRY_HEADER_COUNT, 0);
  }};

  public void validate() {
    Preconditions.checkArgument(nonNull(userId), "User id is required");
    Preconditions.checkArgument(nonNull(currencyFrom), "You must provide a selling currency type");
    Preconditions.checkArgument(nonNull(currencyTo), "You must provide a buying currency type");
    Preconditions.checkArgument(amountBuy.compareTo(BigDecimal.ZERO) > 0, "Buying amount must be greater than 0");
    Preconditions.checkArgument(amountSell.compareTo(BigDecimal.ZERO) > 0, "Selling amount must be greater than 0");
    Preconditions.checkArgument(rate.compareTo(BigDecimal.ZERO) > 0, "Rate must be greater than 0");
  }

}

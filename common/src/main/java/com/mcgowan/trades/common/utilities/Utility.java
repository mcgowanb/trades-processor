package com.mcgowan.trades.common.utilities;

import java.math.BigDecimal;
import java.util.Random;

public class Utility {

  private static final Random random = new Random();

  public static Long randomLong() {
    return random.nextLong();
  }

  public static BigDecimal randomBigDecimal() {
    return BigDecimal.valueOf(randomLong());
  }
}

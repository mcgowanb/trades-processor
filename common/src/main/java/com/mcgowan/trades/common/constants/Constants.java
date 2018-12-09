package com.mcgowan.trades.common.constants;

public class Constants {

  public static final String QUEUE = "trades.queue";

  public static final String RETRY_QUEUE_NAME = "trades.queue.retry";

  public static final String DEAD_LETTER_QUEUE = "trades.queue.dead-letter";

  public static final String EXCHANGE_NAME = "trades.exchange";

  public static final String RETRY_EXCHANGE = "trades.exchange.retry";

  public static final String DEAD_LETTER_EXCHANGE = "trades.exchange.dead-letter";

  public static final String ROUTING_KEY = "trades.router.key";

  public static final String RETRY_ROUTING_KEY = "trades.router.key.retry";

  public static final String DEAD_LETTER_ROUTING_KEY = "trades.router.key.dead-letter";

  public static final Boolean IS_DURABLE = true;

  public static final Boolean IS_EXCLUSIVE = false;

  public static final Boolean AUTO_DELETE = false;
}

package com.mcgowan.trades.web.controller;

import static junit.framework.TestCase.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import com.mcgowan.trades.common.TradeDTO;

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
}

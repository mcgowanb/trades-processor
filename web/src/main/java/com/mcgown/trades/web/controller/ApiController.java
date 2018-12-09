package com.mcgown.trades.web.controller;

import lombok.extern.log4j.Log4j2;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mcgowan.trades.common.DTO.TradeDTO;

@RestController
@Log4j2
@RequestMapping("api")
public class ApiController {

  @PostMapping("/trade")
  public HttpStatus processTrade(@RequestBody final TradeDTO tradeDTO) {
    tradeDTO.validate();
    return HttpStatus.OK;
  }
}

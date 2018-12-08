package com.mcgown.trades.web.controller;

import lombok.extern.log4j.Log4j2;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class ApiController {

  @PostMapping("/trade")
  public HttpStatus processTrade(){
    return HttpStatus.OK;
  }
}

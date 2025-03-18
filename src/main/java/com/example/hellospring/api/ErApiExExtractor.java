package com.example.hellospring.api;

import com.example.hellospring.exchange.ExRateData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;

public class ErApiExExtractor implements ExRateExtractor {

  @Override
  public BigDecimal extractExRate(String response) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(response, ExRateData.class).rates().get("KRW");
  }
}

package com.example.hellospring.api;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.math.BigDecimal;
import java.net.URI;

public class ApiTemplate {
  
  public BigDecimal getExRate(String url, ApiExecutor apiExecutor, ExRateExtractor exRateExtractor) {
    String response;
    try {
      response = apiExecutor.execute(URI.create(url));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    try {
      return exRateExtractor.extractExRate(response);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
  
}

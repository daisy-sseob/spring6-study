package com.example.hellospring.api;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.math.BigDecimal;
import java.net.URI;

public class ApiTemplate {

  private final ApiExecutor defaultApiExecutor;
  private final ExRateExtractor defaultExRateExtractor;

  public ApiTemplate() {
    this.defaultApiExecutor = new HttpClientApiExecutor();
    this.defaultExRateExtractor = new ErApiExExtractor();
  }

  public ApiTemplate(ApiExecutor apiExecutor, ExRateExtractor exRateExtractor) {
    this.defaultApiExecutor = apiExecutor;
    this.defaultExRateExtractor = exRateExtractor;
  }

  public BigDecimal getForExRate(String url) {
    return this.getForExRate(url, this.defaultApiExecutor, this.defaultExRateExtractor);
  }
  
  public BigDecimal getForExRate(String url, ApiExecutor apiExecutor) {
    return this.getForExRate(url, apiExecutor, this.defaultExRateExtractor);
  }
  
  public BigDecimal getForExRate(String url, ExRateExtractor exRateExtractor) {
    return this.getForExRate(url, this.defaultApiExecutor, exRateExtractor);
  }

  public BigDecimal getForExRate(String url, ApiExecutor apiExecutor, ExRateExtractor exRateExtractor) {
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

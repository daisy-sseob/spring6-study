package com.example.hellospring.exchange;

import com.example.hellospring.api.ApiExecutor;
import com.example.hellospring.api.SimpleApiExecutor;
import com.example.hellospring.payment.ExRateProvider;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.net.URI;

@Component
public class WebApiExRateProvider implements ExRateProvider {

  @Override
  public BigDecimal getExRate(String currency) {
    String url = "https://open.er-api.com/v6/latest/" + currency;
    return runApiForExRate(url, new SimpleApiExecutor());
  }
  
  private static BigDecimal runApiForExRate(String url, ApiExecutor apiExecutor) {
    String response;
    try {
      response = apiExecutor.execute(URI.create(url));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    try {
      return extractExRate(response);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  private static BigDecimal extractExRate(String response) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(response, ExRateData.class).rates().get("KRW");
  }

}

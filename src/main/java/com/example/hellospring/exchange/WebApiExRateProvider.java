package com.example.hellospring.exchange;

import com.example.hellospring.api.ApiTemplate;
import com.example.hellospring.payment.ExRateProvider;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class WebApiExRateProvider implements ExRateProvider {
  
  private final ApiTemplate apiTemplate;

  public WebApiExRateProvider(ApiTemplate apiTemplate) {
    this.apiTemplate = apiTemplate;
  }

  @Override
  public BigDecimal getExRate(String currency) {
    
    String url = "https://open.er-api.com/v6/latest/" + currency;

    return apiTemplate.getForExRate(url);
  }
  
}

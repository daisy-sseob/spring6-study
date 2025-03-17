package com.example.hellospring.exchange;

import com.example.hellospring.payment.ExRateProvider;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class WebApiExRateProvider implements ExRateProvider {

  @Override
  public BigDecimal getExRate(String currency) {
    
    HttpClient client = HttpClient.newHttpClient();
    String response;

    try {
      response = client
          .send(
              HttpRequest.newBuilder()
                  .uri(URI.create("https://open.er-api.com/v6/latest/" + currency))
                  .build(),
              HttpResponse.BodyHandlers.ofString()
          )
          .body();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    try {
      
      ObjectMapper mapper = new ObjectMapper();
      ExRateData data = mapper.readValue(response, ExRateData.class);
      System.out.println();
      System.out.println("API ExRate: " + data.rates().get("KRW"));
      System.out.println();
  
      return data.rates().get("KRW");
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }

  }

}

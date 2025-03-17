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
import java.security.interfaces.RSAKey;

@Component
public class WebApiExRateProvider implements ExRateProvider {

  @Override
  public BigDecimal getExRate(String currency) {
    String url = "https://open.er-api.com/v6/latest/" + currency;
    return runApiForExRate(url);
  }

  private static BigDecimal runApiForExRate(String url) {
    String response;
    try {
      response = executeApi(url);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    try {
      return extractExRate(response);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  private static String executeApi(String url) throws IOException, InterruptedException {
    return HttpClient.newHttpClient()
        .send(
            HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build(),
            HttpResponse.BodyHandlers.ofString()
        )
        .body();
  }

  private static BigDecimal extractExRate(String response) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(response, ExRateData.class).rates().get("KRW");
  }

}

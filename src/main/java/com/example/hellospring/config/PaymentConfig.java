package com.example.hellospring.config;

import com.example.hellospring.api.ApiTemplate;
import com.example.hellospring.api.ErApiExExtractor;
import com.example.hellospring.exchange.RestTemplateExRateProvider;
import com.example.hellospring.api.SimpleApiExecutor;
import com.example.hellospring.exchange.CachedExRateProvider;
import com.example.hellospring.payment.ExRateProvider;
import com.example.hellospring.payment.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.time.Clock;

@Configuration
public class PaymentConfig {

  @Bean
  public PaymentService paymentService() {
    return new PaymentService(cachedExRateProvider(), clock());
  }

  @Bean
  public ExRateProvider cachedExRateProvider() {
    return new CachedExRateProvider(exRateProvider());
  }

  @Bean
  public ApiTemplate apiTemplate() {
    return new ApiTemplate(new SimpleApiExecutor(), new ErApiExExtractor());
  }

  @Bean
  public ExRateProvider exRateProvider() {
    return new RestTemplateExRateProvider(restTemplate());
  }

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate(new JdkClientHttpRequestFactory());
  }

  @Bean
  public Clock clock() {
    return Clock.systemDefaultZone();
  }
  
}

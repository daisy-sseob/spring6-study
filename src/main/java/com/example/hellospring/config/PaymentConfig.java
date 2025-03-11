package com.example.hellospring.config;

import com.example.hellospring.exchange.CachedExRateProvider;
import com.example.hellospring.payment.ExRateProvider;
import com.example.hellospring.exchange.WebApiExRateProvider;
import com.example.hellospring.payment.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
	public ExRateProvider exRateProvider() {
		return new WebApiExRateProvider();
	}
	
	@Bean
	public Clock clock() {
		return Clock.systemDefaultZone();
	}

}

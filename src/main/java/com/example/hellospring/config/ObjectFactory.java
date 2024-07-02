package com.example.hellospring.config;

import com.example.hellospring.exchange.CachedExRateProvider;
import com.example.hellospring.exchange.ExRateProvider;
import com.example.hellospring.exchange.WebApiExRateProvider;
import com.example.hellospring.payment.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectFactory {

	@Bean
	public PaymentService paymentService() {
		return new PaymentService(cachedExRateProvider());
	}

	@Bean
	public ExRateProvider cachedExRateProvider() {
		return new CachedExRateProvider(exRateProvider());
	}
	
	@Bean
	public ExRateProvider exRateProvider() {
		return new WebApiExRateProvider();
	}

}

package com.example.hellospring.payment;

import com.example.hellospring.exchange.ExRateProvider;
import com.example.hellospring.payment.exchange.ExRateProviderStub;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

@Configuration
public class TestPaymentConfig {
	
	@Bean
	public PaymentService paymentService() {
		return new PaymentService(exRateProvider(), clock());
	}
	
	@Bean ExRateProvider exRateProvider() {
		return new ExRateProviderStub();
	}
	
	@Bean
	public Clock clock() {
		return Clock.fixed(Instant.now(), ZoneId.systemDefault());
	}
	
}

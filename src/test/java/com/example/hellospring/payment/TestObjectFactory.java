package com.example.hellospring.payment;

import com.example.hellospring.exchange.ExRateProvider;
import com.example.hellospring.payment.exchange.ExRateProviderStub;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestObjectFactory {
	
	@Bean
	public PaymentService paymentService() {
		return new PaymentService(exRateProvider());
	}
	
	@Bean ExRateProvider exRateProvider() {
		return new ExRateProviderStub();
	}
	
}

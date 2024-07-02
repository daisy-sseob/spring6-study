package com.example.hellospring.payment;

import com.example.hellospring.exchange.ExRateProvider;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class PaymentService {

	private final ExRateProvider exRateProvider;

	public PaymentService(ExRateProvider exRateProvider) {
		this.exRateProvider = exRateProvider;
	}

	public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) throws Exception {

		BigDecimal exRate = exRateProvider.getExRate(currency);
		BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exRate);
		LocalDateTime validUntil = LocalDateTime.now().plusMinutes(30);

		return Payment.builder()
				.orderId(orderId)
				.currency(currency)
				.foreignCurrencyAmount(foreignCurrencyAmount)
				.exRate(exRate)
				.convertedAmount(convertedAmount)
				.validUntil(validUntil)
				.build();

	}
	
}

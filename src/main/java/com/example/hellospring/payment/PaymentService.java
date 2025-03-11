package com.example.hellospring.payment;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;

@Service
public class PaymentService {

	private final ExRateProvider exRateProvider;
	private final Clock clock;

	public PaymentService(ExRateProvider exRateProvider, Clock clock) {
		this.exRateProvider = exRateProvider;
		this.clock = clock;
	}

	public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) throws Exception {

		BigDecimal exRate = exRateProvider.getExRate(currency);
		BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exRate);
		LocalDateTime validUntil = LocalDateTime.now(clock).plusMinutes(30);

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

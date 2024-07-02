package com.example.hellospring.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

	private Long orderId;
	private String currency;
	private BigDecimal foreignCurrencyAmount;
	private BigDecimal exRate;
	private BigDecimal convertedAmount;
	private LocalDateTime validUntil;

	@Override
	public String toString() {
		return "Payment{" +
				"orderId=" + orderId +
				", currency='" + currency + '\'' +
				", foreignCurrencyAmount=" + foreignCurrencyAmount +
				", exRate=" + exRate +
				", convertedAmount=" + convertedAmount +
				", validUntil=" + validUntil +
				'}';
	}
}

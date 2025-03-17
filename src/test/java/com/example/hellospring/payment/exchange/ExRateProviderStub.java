package com.example.hellospring.payment.exchange;

import com.example.hellospring.payment.ExRateProvider;

import java.math.BigDecimal;

public class ExRateProviderStub implements ExRateProvider {
	
	@Override
	public BigDecimal getExRate(String currency) {
		return BigDecimal.valueOf(1000);
	}

}

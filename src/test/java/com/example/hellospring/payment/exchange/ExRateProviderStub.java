package com.example.hellospring.payment.exchange;

import com.example.hellospring.exchange.ExRateProvider;

import java.math.BigDecimal;

public class ExRateProviderStub implements ExRateProvider {
	
	@Override
	public BigDecimal getExRate(String currency) throws Exception {
		return BigDecimal.valueOf(1000);
	}

}

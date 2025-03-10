package com.example.hellospring.exchange;

import com.example.hellospring.payment.ExRateProvider;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * WebApiExRateProvider 의 decorator class
 */
public class CachedExRateProvider implements ExRateProvider {

	private final ExRateProvider target;
	private BigDecimal cachedExRate;
	private LocalDateTime cacheExpiryTime;

	public CachedExRateProvider(ExRateProvider target) {
		this.target = target;
	}

	@Override
	public BigDecimal getExRate(String currency) throws Exception {

		if (cachedExRate == null || cacheExpiryTime.isBefore(LocalDateTime.now())) {
			cachedExRate = target.getExRate(currency);
			cacheExpiryTime = LocalDateTime.now().plusSeconds(3);
			System.out.println(" >>> cache update <<< ");
		}
		
		return cachedExRate;
	}
	
}

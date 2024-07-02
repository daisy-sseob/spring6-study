package com.example.hellospring.exchange;

import java.math.BigDecimal;

public interface ExRateProvider {
	
	BigDecimal getExRate(String currency) throws Exception;
	
}

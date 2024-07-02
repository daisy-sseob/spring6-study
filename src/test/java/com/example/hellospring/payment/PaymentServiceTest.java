package com.example.hellospring.payment;

import com.example.hellospring.exchange.WebApiExRateProvider;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PaymentServiceTest {

	@Test
	@DisplayName("prepare 메서드가 요구사항 3가지를 잘 충족했는지 검증한다.")
	void prepare() throws Exception {

		PaymentService paymentService = new PaymentService(new WebApiExRateProvider());

		Payment payment = paymentService.prepare(100L, "USD", BigDecimal.TEN);

		// 환율 정보가 있는지 확인
		assertThat(payment.getExRate()).isNotNull();
		
		// 환산금액 검증 
		assertThat(payment.getConvertedAmount())
				.isEqualTo(payment.getExRate().multiply(payment.getForeignCurrencyAmount()));
		
		// 유효시간 계산
		assertThat(payment.getValidUntil()).isAfter(LocalDateTime.now());
		assertThat(payment.getValidUntil()).isBefore(LocalDateTime.now().plusMinutes(30));
		
	}
	
}
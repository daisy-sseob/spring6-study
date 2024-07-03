package com.example.hellospring.payment.payment;

import com.example.hellospring.payment.TestPaymentConfig;
import com.example.hellospring.payment.Payment;
import com.example.hellospring.payment.PaymentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestPaymentConfig.class})
class PaymentServiceTest {
	
	@Autowired
	PaymentService paymentService;

	@Test
	@DisplayName("prepare 메서드가 요구사항 3가지를 잘 충족했는지 검증한다.")
	void prepare() throws Exception {

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
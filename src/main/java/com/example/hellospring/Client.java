package com.example.hellospring;

import com.example.hellospring.config.PaymentConfig;
import com.example.hellospring.payment.Payment;
import com.example.hellospring.payment.PaymentService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

public class Client {
	public static void main(String[] args) throws Exception {
		BeanFactory factory = new AnnotationConfigApplicationContext(PaymentConfig.class);
		PaymentService paymentService = factory.getBean(PaymentService.class);
		Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
		System.out.println("Payment" + payment);
		System.out.println("=====================================");
		System.out.println();
		
		Payment payment2 = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
		System.out.println("Payment2" + payment2);
		System.out.println("=====================================");
		System.out.println();

		TimeUnit.SECONDS.sleep(3);
		
		Payment payment3 = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
		System.out.println("Payment3" + payment3);
		System.out.println("=====================================");
		System.out.println();
		
	}
}

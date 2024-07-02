package com.example.hellospring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

public class Client {
	
	public static void main(String[] args) throws Exception {
		BeanFactory factory = new AnnotationConfigApplicationContext(ObjectFactory.class);
		PaymentService paymentService = factory.getBean(PaymentService.class);
		Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
		System.out.println(payment);
		
	}
}

package com.example.hellospring;

import com.example.hellospring.config.OrderConfig;
import com.example.hellospring.order.Order;
import com.example.hellospring.order.OrderService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

public class OrderClient {

  public static void main(String[] args) {

    BeanFactory beanFactory = new AnnotationConfigApplicationContext(OrderConfig.class);
    OrderService orderService = beanFactory.getBean(OrderService.class);
    Order order = orderService.createOrder("0100", BigDecimal.TEN);

    System.out.println(order);

  }

}

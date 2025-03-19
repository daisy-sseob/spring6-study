package com.example.hellospring;

import com.example.hellospring.config.DataSourceConfig;
import com.example.hellospring.order.Order;
import com.example.hellospring.order.OrderRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

public class DataClient {

  public static void main(String[] args) {
   
    BeanFactory beanFactory = new AnnotationConfigApplicationContext(DataSourceConfig.class);
    OrderRepository orderRepository = beanFactory.getBean(OrderRepository.class);
    
    orderRepository.save(new Order("1239", BigDecimal.TEN));
    orderRepository.save(new Order("1239", BigDecimal.TEN));

  }
  
}

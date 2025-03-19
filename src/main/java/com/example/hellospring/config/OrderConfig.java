package com.example.hellospring.config;

import com.example.hellospring.data.JpaOrderRepository;
import com.example.hellospring.order.OrderRepository;
import com.example.hellospring.order.OrderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@Import(DataSourceConfig.class)
public class OrderConfig {
  @Bean
  public OrderRepository orderRepository() {
    return new JpaOrderRepository();
  }
  
  @Bean
  public OrderService orderService(PlatformTransactionManager transactionManager) {
    return new OrderService(orderRepository(), transactionManager);
  }
}

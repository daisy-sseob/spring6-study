package com.example.hellospring.config;

import com.example.hellospring.data.JdbcOrderRepository;
import com.example.hellospring.data.JpaOrderRepository;
import com.example.hellospring.order.Order;
import com.example.hellospring.order.OrderRepository;
import com.example.hellospring.order.OrderService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@Import(DataSourceConfig.class)
public class OrderConfig {
//  @Bean
//  public OrderRepository orderRepository() {
//    return new JpaOrderRepository();
//  }
  @Bean
  public OrderRepository orderRepository(DataSource dataSource) {
    return new JdbcOrderRepository(dataSource);
  }
  
  @Bean
  public OrderService orderService(OrderRepository orderRepository, @Qualifier(value = "jdbcTransactionManager") PlatformTransactionManager jdbcTransactionManager) {
    return new OrderService(orderRepository, jdbcTransactionManager);
  }
}

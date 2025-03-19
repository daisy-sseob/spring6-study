package com.example.hellospring;

import com.example.hellospring.config.DataSourceConfig;
import com.example.hellospring.order.Order;
import com.example.hellospring.order.OrderRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;

public class DataClient {

  public static void main(String[] args) {

    BeanFactory beanFactory = new AnnotationConfigApplicationContext(DataSourceConfig.class);
    JpaTransactionManager transactionManager = beanFactory.getBean(JpaTransactionManager.class);
    OrderRepository orderRepository = beanFactory.getBean(OrderRepository.class);

    // transaction begin
    try {
      TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
      transactionTemplate.execute(status -> {

        orderRepository.save(new Order("1239", BigDecimal.TEN));
        orderRepository.save(new Order("1239", BigDecimal.TEN));

        return null;
      });

    } catch (DataIntegrityViolationException e) {
      System.out.println();
      System.out.println("================> 주문번호 중복 복구 작업");
      System.out.println();
      e.printStackTrace();
    }

  }

}

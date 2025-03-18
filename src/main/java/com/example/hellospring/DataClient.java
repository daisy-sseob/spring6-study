package com.example.hellospring;

import com.example.hellospring.config.DataSourceConfig;
import com.example.hellospring.order.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

public class DataClient {

  public static void main(String[] args) {
   
    BeanFactory beanFactory = new AnnotationConfigApplicationContext(DataSourceConfig.class);
    EntityManagerFactory emf = beanFactory.getBean(EntityManagerFactory.class);

    EntityManager em = emf.createEntityManager();

    em.getTransaction().begin();

    Order order = new Order("100", BigDecimal.TEN);
    
    em.persist(order);

    System.out.println(order);
    
    em.getTransaction().commit();
    em.close();
  }
  
}

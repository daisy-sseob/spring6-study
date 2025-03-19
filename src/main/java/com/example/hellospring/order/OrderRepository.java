package com.example.hellospring.order;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

public class OrderRepository {
  
  private final EntityManagerFactory entityManagerFactory;

  public OrderRepository(EntityManagerFactory entityManagerFactory) {
    this.entityManagerFactory = entityManagerFactory;
  }

  public void save(Order order) {
    
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();
    
    transaction.begin();

    try {

      entityManager.persist(order);
      entityManager.flush();
      transaction.commit();
      
    } catch (RuntimeException e) {
      
      if (transaction.isActive()) transaction.rollback();
      throw e;
      
    }finally {
      if (entityManager.isOpen()) entityManager.close();
    }
    
  }
  
}

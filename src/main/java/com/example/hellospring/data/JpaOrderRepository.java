package com.example.hellospring.order;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class JpaOrderRepository implements OrderRepository {

  @PersistenceContext
  private EntityManager entityManager;

  public void save(Order order) {
    entityManager.persist(order);
  }

}

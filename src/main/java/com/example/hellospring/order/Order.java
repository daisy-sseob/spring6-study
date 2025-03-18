package com.example.hellospring.order;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@Getter
public class Order {
  
  @Id @GeneratedValue
  private Long id;
  
  @Column(unique = true)
  private String no;
  
  private BigDecimal total;

  public Order(String no, BigDecimal total) {
    this.no = no;
    this.total = total;
  }

  @Override
  public String toString() {
    return "Order{" +
        "id=" + id +
        ", no='" + no + '\'' +
        ", total=" + total +
        '}';
  }
}

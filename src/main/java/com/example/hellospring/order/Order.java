package com.example.hellospring.order;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class Order {
  
  private Long id;
  
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

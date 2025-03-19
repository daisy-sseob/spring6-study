package com.example.hellospring.payment.order;

import com.example.hellospring.config.OrderConfig;
import com.example.hellospring.order.OrderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.BDDAssertions.then;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = OrderConfig.class)
public class OrderServiceSpringTest {
  @Autowired
  private OrderService orderService;

  @Test
  @DisplayName("order 생성 테스트")
  void createOrder() {
    // given, when
    var order = orderService.createOrder("1001", BigDecimal.TWO);

    // then
    then(order.getId()).isGreaterThan(0);
    then(order.getNo()).isEqualTo("1001");
    then(order.getTotal()).isEqualTo(BigDecimal.TWO);
    
  }
}

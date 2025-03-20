package com.example.hellospring.payment.order;

import com.example.hellospring.config.OrderConfig;
import com.example.hellospring.order.OrderReq;
import com.example.hellospring.order.OrderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.BDDAssertions.then;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = OrderConfig.class)
public class OrderServiceImplSpringTest {
  @Autowired
  private OrderService orderService;

  @Autowired
  private DataSource dataSource;

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

  @Test
  @DisplayName("orders 여러 건 생성 테스트")
  void createOrders() {
    
    // given
    List<OrderReq> orderReqs = List.of(
        new OrderReq("0200", BigDecimal.ONE),
        new OrderReq("0201", BigDecimal.ONE)
    );
    
    // when
    var orders = orderService.createOrders(orderReqs);
    
    // then
    then(orders).hasSize(2);
    orders.forEach(order -> then(order.getId()).isGreaterThan(0));
    
  }
  
  @Test
  @DisplayName("orders 여러 건 생성 rollback 테스트")
  void createDuplicatedOrders() {
    
    // given
    List<OrderReq> orderReqs = List.of(
        new OrderReq("0200", BigDecimal.ONE),
        new OrderReq("0200", BigDecimal.ONE)
    );

    // when
    assertThatThrownBy(() -> orderService.createOrders(orderReqs));

    // then
    JdbcClient client = JdbcClient.create(dataSource);
    Integer count = client.sql("select count(*) from orders")
        .query(Integer.class)
        .single();

    then(count).isEqualTo(0);
    
  }
  
}

package com.beyond3.yyGang.order.repository;

import com.beyond3.yyGang.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findOrdersByUser_UserId(Long userId);

}

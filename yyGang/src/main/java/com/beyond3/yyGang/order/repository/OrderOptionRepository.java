package com.beyond3.yyGang.order.repository;

import com.beyond3.yyGang.order.domain.Order;
import com.beyond3.yyGang.order.domain.OrderOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderOptionRepository extends JpaRepository<OrderOption, Long> {

    List<OrderOption> findByOrderId(Order orderId);


}
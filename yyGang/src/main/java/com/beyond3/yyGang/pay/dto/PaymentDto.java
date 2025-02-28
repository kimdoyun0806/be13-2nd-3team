package com.beyond3.yyGang.pay.dto;

import com.beyond3.yyGang.order.domain.Order;
import com.beyond3.yyGang.pay.Payment;
import lombok.Data;

@Data
public class PaymentDto {

    private int totalPrice; // 전체 가격

    private String payMethod; // 결제 방법

    private Long orderId;   // 주문 아이디

    public Payment toEntity(Order order){
        return Payment.builder()
                .payMethod(payMethod)
                .totalPrice(totalPrice)
                .order(order)
                .build();
    }

}
package com.beyond3.yyGang.order.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private long supplement_id; // 영양제 아이디

    private int count;  // 영양제 수량
}
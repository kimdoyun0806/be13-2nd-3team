package com.beyond3.yyGang.order.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class OrderDto {

    @NotNull(message = "상품 아이디는 필수 입력 값입니다.")
    private Long productId;

    @Min(value = 1, message = "최소 주문 수량은 1개 입니다.")
    private int count;
}
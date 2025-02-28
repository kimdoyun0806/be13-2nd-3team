package com.beyond3.yyGang.cart.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class CartOptionDto {

    private Long nSupplementId;    // 영양제 아이디

    private int quantity; // 수량

    private int price;  // 가격

}
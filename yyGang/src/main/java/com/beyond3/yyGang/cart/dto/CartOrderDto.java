package com.beyond3.yyGang.cart.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data
public class CartOrderDto {

    private Long cartProductId;
    private List<CartOrderDto> cartOrderDtoList;
}
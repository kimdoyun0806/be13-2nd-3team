package com.beyond3.yyGang.cart.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
public class CartListDto {

    private Long cartOptionID;

    // null조건 추가
    private int quantity;

    private int price;

    private String productName;

    private String brand;


    // 생성자 추가
    public CartListDto(Long cartOptionID, String productName, int price, int quantity, String brand) {
        this.cartOptionID = cartOptionID;
        this.quantity = quantity;
        this.price = price;
        this.productName = productName;
        this.brand = brand;
    }
}
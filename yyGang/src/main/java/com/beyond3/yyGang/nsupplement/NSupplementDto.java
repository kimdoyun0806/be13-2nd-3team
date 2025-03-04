package com.beyond3.yyGang.nsupplement;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class NSupplementDto {
    private Long productId;

    private String productName;

    private String brand;  // 브랜드

    private int price;  // 상품 가격

    public NSupplementDto() {
    }

    public NSupplementDto(NSupplement nSupplement) {
        this.productId= nSupplement.getProductId();
        this.productName= nSupplement.getProductName();
        this.price = nSupplement.getPrice();
        this.brand = nSupplement.getBrand();
    }
}

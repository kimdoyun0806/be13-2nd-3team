package com.beyond3.yyGang.nsupplement.domain;

import com.beyond3.yyGang.nsupplement.dto.NSupplementFormDto;
import com.beyond3.yyGang.nsupplement.dto.OutOfStockException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Builder
@Table(name = "n_supplement")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class NSupplement {

    // 영양제
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(nullable = false)
    private String productName; // 상품 이름

    @Column(columnDefinition = "TEXT")
    private String caution; // 주의 사항

    @Column(nullable = false)
    private String brand;  // 브랜드

    @Column(nullable = false)
    private int price;  // 상품 가격

    @Column(nullable = false)
    private int stockQuantity;

    // 영양제 수정
    public void updateSupplement(NSupplementFormDto nSupplementFormDto) {
        this.productName = nSupplementFormDto.getSupplementName();
        this.price = nSupplementFormDto.getPrice();
        this.stockQuantity = nSupplementFormDto.getStockQuantity();
        this.caution = nSupplementFormDto.getCaution();
    }

    // 영양제 재고 마이너스
    public void removeStock(int stockQuantity) {
        int restStock = this.stockQuantity - stockQuantity;
        if (restStock < 0) {
            throw new OutOfStockException("상품의 재고가 부족합니다. 현재 재고 수량 : " + this.stockQuantity + ")");
        }
    }

    // 영양제 재고 플러스
    public void addStock(int stockQuantity) {
        this.stockQuantity += stockQuantity;
    }


}

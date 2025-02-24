package com.beyond3.yyGang.cart.domain;

import com.beyond3.yyGang.nsupplement.domain.NSupplement;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Table(name = "cart_option")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CartOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_option_id")
    private Long cartOptionID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id",nullable = false)
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "products_id",nullable = false)
    private NSupplement nSupplement;

    private int quantity; // 수량

    private int totalPrice;  // 가격

    // 생성 메소드
    // 장바구니에 담긴 물품 하나 당 가격과 수량
    public static CartOption createCartSupplements(Cart cart, NSupplement nSupplements, int quantity ) {
        return CartOption.builder()
                .cart(cart)
                .nSupplement(nSupplements)
                .quantity(quantity)
                .build();
    }

    // 상품의 총 가격(한 상품에 대한 가격)
    public int getTotalPrice() {
        return totalPrice * quantity;
    }

    // 상품 추가할 때 가격 업데이트 (일단 이건 보류)
    public void updatePrice(int totalPrice) {
        this.totalPrice += totalPrice;
    }

    // 이미 담겨 있는 물건 또 담을 경우 수량 증가
    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    // 장바구니 상품 수량 변경
    public void updateQuantity(int quantity) {
        this.quantity = quantity;
    }


}

package com.beyond3.yyGang.cart.domain;

import com.beyond3.yyGang.nsupplement.NSupplement;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Table(name = "cart_option")
public class CartOption {
    @Id
    @GeneratedValue
    @Column(name = "cart_option_id")
    private Long cartOptionID;

    private int quantity; // 수량

    private int price;  // 가격

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "products_id")
    private NSupplement nSupplement;

    protected CartOption() {
    }

    private CartOption(Cart cart, NSupplement nSupplement, int quantity) {
        this.cart = cart;
        this.nSupplement = nSupplement;
        this.quantity = quantity;
        this.price = calculateCartOptionPrice();
//        this.cart.addCartOption(this);
    }

    public static CartOption createCartOption(Cart cart, NSupplement nSupplement, int quantity) {
        return new CartOption(cart, nSupplement, quantity);
    }

    // 장바구니옵션 상품 수량 * 가격
    private int calculateCartOptionPrice() {
        return this.nSupplement.getPrice() * this.quantity;
    }

    // 장바구니 상품 수량,가격 변경
    public void updateSupplement(int quantity) {
        this.quantity = quantity;
        this.price = calculateCartOptionPrice();
    }

}
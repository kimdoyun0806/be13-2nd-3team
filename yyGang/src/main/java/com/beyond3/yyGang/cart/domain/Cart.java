package com.beyond3.yyGang.cart.domain;

import com.beyond3.yyGang.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Getter
@Table(name = "cart")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long cartId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartOption> cartOptions = new ArrayList<>();

    private int count; // 카트에 담긴 총 상품 개수


    //연관관계 편의 메소드
    /* 비즈니스 로직을 짤 때 편의성을 위함(양방향 연관관계?)
         Users user = new Users();
         Cart cart = new Cart();
         user.getCart().add(cart);
         cart.setUser(user);
    */

    @Builder
    public Cart(User user) {
        this.user = user;
    }

    public static Cart createCart(User user) {
        return Cart.builder()
                .user(user)
                .build();
    }
}
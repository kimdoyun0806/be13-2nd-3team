package com.beyond3.yyGang.order.domain;

import com.beyond3.yyGang.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    //주문

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderID;  // 주문 고유 ID

    // 한 회원이 여러 개의 주문을 생성 가능 -> 주문 입장에서 회원은 다대일 관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private LocalDateTime orderDate;

    //    @OneToOne(mappedBy = "order", fetch = FetchType.LAZY)
//    private Payment payment;
//
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OrderOption> orderOptions = new ArrayList<>();

    public void addOrderOption(OrderOption orderOption) {
        orderOptions.add(orderOption);
        orderOption.setOrder(this);
    }

    public static Order createOrder(User user, List<OrderOption> orderOptionList) {
        Order order = new Order();
        order.setUser(user);
        for (OrderOption orderOption : orderOptionList) {
            order.addOrderOption(orderOption);
        }
        order.setOrderDate(LocalDateTime.now());
        order.setOrderStatus(OrderStatus.ORDERED);
        return order;
    }

    public int getTotalPrice(){
        int totalPrice = 0;

        for (OrderOption orderOption : orderOptions) {
            totalPrice += orderOption.getTotalPrice();
        }
        return totalPrice;
    }

    public void orderCancel() {
        this.orderStatus = OrderStatus.CANCELED;
        for (OrderOption orderOption : orderOptions) {
            orderOption.cancel();
        }
    }
}

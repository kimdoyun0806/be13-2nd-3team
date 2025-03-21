package com.beyond3.yyGang.order.domain;

import com.beyond3.yyGang.EntityDate;
import com.beyond3.yyGang.user.domain.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "`order`")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends EntityDate {
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

    @OneToMany(mappedBy = "orderId", cascade = CascadeType.ALL)
    private List<OrderOption> orderOptions;

    private int totalOrderPrice;

    @Builder
    private Order(User user, OrderStatus orderStatus) {
        this.user = user;
        this.orderStatus = orderStatus;
    }

    // Order를 생성한다고 명시적으로 표현
    public static Order createOrder(User user) {

        return Order.builder()
                .user(user)
                .orderStatus(OrderStatus.PENDING) // 기본적으로 PENDING 상태로 설정
                .build();
    }

    //    @OneToOne(mappedBy = "order", fetch = FetchType.LAZY)
//    private Payment payment;

//    private Order(User user, OrderOption... orderOptions) {
//        this.user = user;
//        // Order에 OrderOption객체를 저장하고 OrderOption에 Order객체를 저장
//        for (OrderOption orderOption : orderOptions) {
//            this.orderOptions.add(orderOption);
//            orderOption.setOrder(this);
//        }
//    }
}
package com.beyond3.yyGang.order.domain;

import com.beyond3.yyGang.nsupplement.domain.NSupplement;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "order_option")
public class OrderOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_option_id")
    private Long orderOptionId;  // 주문 옵션 아이디

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "products_ID")
    private NSupplement nSupplement;

    private int quantity;  // 수량

    private int price;  // 가격

    public static OrderOption createOrderOption(NSupplement nSupplement, int quantity) {
        OrderOption orderOption = new OrderOption();
        orderOption.setNSupplement(nSupplement);
        orderOption.setQuantity(quantity);
        orderOption.setPrice(nSupplement.getPrice());

        nSupplement.removeStock(quantity);
        return orderOption;
    }

    public int getTotalPrice() {
        return this.price * this.quantity;
    }

    public void cancel() {
        this.getNSupplement().addStock(quantity);

    }

}

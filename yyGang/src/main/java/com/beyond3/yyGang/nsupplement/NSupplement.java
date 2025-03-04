package com.beyond3.yyGang.nsupplement;

import com.beyond3.yyGang.productCategory.ProductCategory;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "n_supplement")
public class NSupplement {

    // 영양제
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    private String productName; // 상품 이름

    @Column(columnDefinition = "TEXT")
    private String caution; // 주의 사항

    private String brand;  // 브랜드

    private int price;  // 상품 가격

    private int stockQuantity;

    @OneToMany(mappedBy = "nSupplement", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProductCategory> productCategories = new HashSet<>();

//    @OneToMany(mappedBy = "nSupplements")
//    private List<Review> reviews;
//
//    @OneToMany(mappedBy = "nSupplements")
//    private List<OrderOption> orderOptions;
//
//    @OneToMany(mappedBy = "supplements")
//    private List<NQuestion> nQuestions;
//
//    @OneToMany(mappedBy = "nSupplements")
//    private List<HFunctionalCategory> hFunctionalCategories;
//
//    @OneToMany(mappedBy = "nSupplements")
//    private List<CartOption> cartOptions;

    public NSupplementRegisterDto toDto(){
        return NSupplementRegisterDto.builder()
                .productName(productName)
                .brand(this.brand)
                .caution(this.caution)
                .price(this.price)
                .stockQuantity(this.stockQuantity)
                .build();
    }

    public void decreaseStockQuantity(int quantity){
        if(stockQuantity - quantity < 0){
            throw new IllegalStateException("재고가 충분하지 않습니다.");
        }
        this.stockQuantity -= quantity;
    }

    public void increaseStockQuantity(int quantity){
        this.stockQuantity += quantity;
    }
}
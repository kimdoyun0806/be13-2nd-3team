//package com.beyond3.yyGang.productCategory;
//
//import com.beyond3.yyGang.category.Category;
//import com.beyond3.yyGang.nsupplement.NSupplement;
//import jakarta.persistence.*;
//import lombok.Getter;
//
//@Getter
//@Entity
//@Table(name= "product_category")
//public class ProductCategory {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "product_category_id")
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "product_id")
//    private NSupplement nSupplement;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "category_id")
//    private Category category;
//
//    protected ProductCategory() {
//    }
//
//    ProductCategory(NSupplement nSupplement, Category category) {
//        this.nSupplement = nSupplement;
//        this.category = category;
//    }
//}

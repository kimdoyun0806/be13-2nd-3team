package com.beyond3.yyGang.nsupplement.dto;

import com.beyond3.yyGang.hfunction.HFunctionName;
import com.beyond3.yyGang.ingredient.IngredientName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NSupplementResponseDtoV2 {

    private Long productId;

    private String productName; // 상품 이름

    private String caution; // 주의 사항

    private String brand;  // 브랜드

    private int price;  // 상품 가격

    private int reviewCount;    // 전체 리뷰 수

    private List<IngredientName> ingredients = new ArrayList<>();

    private List<HFunctionName> healthNames = new ArrayList<>();

    public NSupplementResponseDtoV2(Long productId, String productName, String caution, String brand, int price) {
        this.productId = productId;
        this.productName = productName;
        this.caution = caution;
        this.brand = brand;
        this.price = price;
    }

    public void addIngredient(IngredientName ingredient) {
        this.ingredients.add(ingredient);
    }

    public void addHealthName(HFunctionName healthName) {
        this.healthNames.add(healthName);
    }

}
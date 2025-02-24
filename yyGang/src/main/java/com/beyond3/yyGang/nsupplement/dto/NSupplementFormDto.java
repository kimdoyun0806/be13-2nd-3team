package com.beyond3.yyGang.nsupplement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NSupplementFormDto {

    private Long id;

    @NotBlank(message = "상품영을 필수 입력 값입니다.")
    private String supplementName;

    @NotNull(message = "가격은 필수 입력 값입니다.")
    private int price;

    @NotNull(message = "재고는 필수 입력 값입니다.")
    private int stockQuantity;

    @NotBlank(message = "상품 상세는 필수 입력 값입니다.")
    private String caution;


}

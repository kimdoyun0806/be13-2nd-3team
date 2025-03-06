package com.beyond3.yyGang.handler.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CartExceptionMessage {

    SUPPLEMENT_ALREADY_EXISTS("이미 장바구니에 담긴 상품입니다.", HttpStatus.CONFLICT),
    STOCK_QUANTITY_NOT_ENOUGH("재고가 충분하지 않습니다.", HttpStatus.CONFLICT),
    CART_COUNT_MIN_VIOLATION("최소 1개 이상 담아야 합니다.",HttpStatus.BAD_REQUEST),
    CART_NOT_FOUND("유저의 장바구니를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    CART_OPTION_PRODUCT_NOT_FOUND("장바구니에서 영양제를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    PRODUCT_NOT_FOUND("상품을 찾을 수 없습니다",HttpStatus.NOT_FOUND),
    CATEGORY_NOT_FOUND("카테고리를 찾을 수 없습니다.",HttpStatus.NOT_FOUND);

    private final String message;
    private final HttpStatus status;
}

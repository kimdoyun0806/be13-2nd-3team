package com.beyond3.yyGang.handler.message;

import com.beyond3.yyGang.handler.exception.CategoryException;
import lombok.Getter;

@Getter
public class ProductEntityException extends CategoryException {
    public ProductEntityException(CategoryExceptionMessage message) {
        super(message);
    }

}

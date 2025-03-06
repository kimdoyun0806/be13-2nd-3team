package com.beyond3.yyGang.handler.message;

import com.beyond3.yyGang.handler.exception.CategoryException;
import lombok.Getter;

@Getter
public class CategoryEntityException extends CategoryException {

    public CategoryEntityException(CategoryExceptionMessage message) {
        super(message);
    }
}

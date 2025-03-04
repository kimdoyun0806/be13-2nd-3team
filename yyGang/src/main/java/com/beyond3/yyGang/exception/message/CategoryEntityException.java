package com.beyond3.yyGang.exception.message;

import com.beyond3.yyGang.exception.BaseException;
import lombok.Getter;

@Getter
public class CategoryEntityException extends BaseException {

    public CategoryEntityException(ExceptionMessage message) {
        super(message);
    }
}

package com.beyond3.yyGang.exception.message;

import com.beyond3.yyGang.exception.BaseException;
import lombok.Getter;

@Getter
public class ProductEntityException extends BaseException {
    public ProductEntityException(ExceptionMessage message) {
        super(message);
    }

}

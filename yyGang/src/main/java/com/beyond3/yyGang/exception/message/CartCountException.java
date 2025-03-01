package com.beyond3.yyGang.exception.message;

import com.beyond3.yyGang.exception.BaseException;
import lombok.Getter;

@Getter
public class CartCountException extends BaseException {

    public CartCountException(ExceptionMessage message) {
        super(message);
    }
}

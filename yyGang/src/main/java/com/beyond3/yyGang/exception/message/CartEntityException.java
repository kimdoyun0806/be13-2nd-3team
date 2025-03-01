package com.beyond3.yyGang.exception.message;


import com.beyond3.yyGang.exception.BaseException;
import lombok.Getter;

@Getter
public class CartEntityException extends BaseException {

    public CartEntityException(ExceptionMessage message) {
        super(message);
    }

}
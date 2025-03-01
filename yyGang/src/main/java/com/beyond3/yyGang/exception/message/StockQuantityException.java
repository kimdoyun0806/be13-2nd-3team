package com.beyond3.yyGang.exception.message;

import com.beyond3.yyGang.exception.BaseException;
import lombok.Getter;

@Getter
public class StockQuantityException extends BaseException {


    public StockQuantityException(ExceptionMessage message) {
        super(message);
    }


}
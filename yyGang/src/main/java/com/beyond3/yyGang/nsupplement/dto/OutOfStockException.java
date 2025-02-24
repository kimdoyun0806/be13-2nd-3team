package com.beyond3.yyGang.nsupplement.dto;

public class OutOfStockException extends RuntimeException{
    public OutOfStockException(String message) {
        super(message);
    }
}

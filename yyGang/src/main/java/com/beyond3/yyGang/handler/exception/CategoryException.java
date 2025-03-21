//package com.beyond3.yyGang.handler.exception;
//
//import lombok.Getter;
//import org.springframework.http.HttpStatus;
//
//import java.io.Serial;
//
//@Getter
//public abstract class CategoryException extends RuntimeException {
//
//    @Serial
//    private static final long serialVersionUID = -3325163702920695888L;
//
//    private final String type;
//    private final HttpStatus status;
//
//    protected CategoryException(CategoryExceptionMessage message) {
//        super(message.getMessage());
//        this.type = message.name();
//        this.status = message.getStatus();
//    }
//
//}
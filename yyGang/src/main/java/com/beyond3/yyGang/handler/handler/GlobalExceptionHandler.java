package com.beyond3.yyGang.handler.handler;

import com.beyond3.yyGang.handler.dto.ErrorResponse;
import com.beyond3.yyGang.handler.exception.CartException;
import com.beyond3.yyGang.handler.exception.OrderException;
import com.beyond3.yyGang.handler.exception.UserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorResponse> handleUserException(UserException e) {
        return new ResponseEntity<>(
                new ErrorResponse(
                        e.getStatus().value(),
                        e.getType(),
                        e.getMessage()),
                e.getStatus()
        );
    }

    @ExceptionHandler(OrderException.class)
    public ResponseEntity<ErrorResponse> handleOrderException(OrderException e) {
        return new ResponseEntity<>(
                new ErrorResponse(
                        e.getStatus().value(),
                        e.getType(),
                        e.getMessage()),
                e.getStatus()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleException(MethodArgumentNotValidException e) {
        StringBuilder errors = new StringBuilder();

        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            errors
                    .append(fieldError.getField())
                    .append("(")
                    .append(fieldError.getDefaultMessage())
                    .append("), ");
        }

        errors.replace(errors.lastIndexOf(","), errors.length(), "");

        return new ResponseEntity<>(
                new ErrorResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        HttpStatus.BAD_REQUEST.name(),
                        errors.toString()
                ),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {

        log.error("Global Exception : {}", e.getMessage());

        return new ResponseEntity<>(
                new ErrorResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        HttpStatus.INTERNAL_SERVER_ERROR.name(),
                        e.getMessage()
                ),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(CartException.class)
    public ResponseEntity<ErrorResponse> handleOrderException(CartException e) {
        return new ResponseEntity<>(
                new ErrorResponse(
                        e.getStatus().value(),
                        e.getType(),
                        e.getMessage()),
                e.getStatus()
        );
    }
}
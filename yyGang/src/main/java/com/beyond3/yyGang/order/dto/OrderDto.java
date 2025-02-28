package com.beyond3.yyGang.order.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class OrderDto {

    private long supplement_id;

    private int count;
}
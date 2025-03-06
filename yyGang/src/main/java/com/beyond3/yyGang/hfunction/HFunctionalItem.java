package com.beyond3.yyGang.hfunction;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
@Table(name = "h_functional_item")
public class HFunctionalItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "health_id")
    private Long healthId;

    @Enumerated(EnumType.STRING)
    private HFunctionName healthName; // 건강 기능 내용

}
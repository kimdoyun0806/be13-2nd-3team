package com.beyond3.yyGang.account;

import com.beyond3.yyGang.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Primary;

import java.security.PrivateKey;

@Getter
@Entity
@Table(name = "personal_account")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personal_account_id")
    private Long accountId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private int balance;

}

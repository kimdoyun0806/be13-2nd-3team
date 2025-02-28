package com.beyond3.yyGang.security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.pl.NIP;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDto {

    @NotBlank(message = "이메일 입력은 필수입니다.")
    @Email
    private String email;

    @NotBlank(message = "비밀번호 입력은 필수입니다.")
    private String password;
}
package com.beyond3.yyGang.user.dto;

import com.beyond3.yyGang.user.domain.Gender;
import com.beyond3.yyGang.user.domain.Role_name;
import com.beyond3.yyGang.user.domain.User;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
public class UserInfoDto {

    private String email;

    private String name;

    private String password;

    // CUSTOMER, SELLER, PHARMACIST, ADMIN -> default : CUSTOMER
    private Role_name role;

    private Integer age;  // 생년 월일로 받을까?

    private Gender gender;  // 성별 -> MALE, FEMALE -> Null 가능

    private String phone; // 전화번호

    private String address; // 주소

    private LocalDateTime createdDate;  // 가입 일자

    // UserInfoDto -> User 엔티티로 변환
//    public User toEntity() {
//        return new User(this.userId, this.email, this.password, this.name, this.role,
//                this.age, this.gender);
//    }

}

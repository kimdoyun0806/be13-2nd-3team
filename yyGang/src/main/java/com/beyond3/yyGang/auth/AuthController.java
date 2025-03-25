package com.beyond3.yyGang.auth;

import com.beyond3.yyGang.auth.dto.JwtToken;
import com.beyond3.yyGang.auth.dto.UserLoginDto;
import com.beyond3.yyGang.auth.service.AuthService;
import com.beyond3.yyGang.handler.exception.UserException;
import com.beyond3.yyGang.handler.message.ExceptionMessage;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="http://localhost:8080")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // 로그인 API
    @PostMapping("/login")
    public ResponseEntity<JwtToken> login(@RequestBody @Valid UserLoginDto userLoginDto) {
        try {
            JwtToken jwtToken = authService.signIn(userLoginDto.getEmail(), userLoginDto.getPassword());
            return ResponseEntity.ok(jwtToken);  // 로그인 성공 시 JWT 토큰 반환
        } catch (UserException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);  // 인증 실패 시 401 반환
        }
    }
}

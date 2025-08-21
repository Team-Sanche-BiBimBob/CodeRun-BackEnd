package com.sanchae.coderun.global.service;

import com.sanchae.coderun.domain.auth.dto.EmailLoginRequestDto;
import com.sanchae.coderun.global.dto.ResponseAccessToken;
import com.sanchae.coderun.domain.auth.service.AuthService;
import com.sanchae.coderun.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenAuthenticationService {

    private final AuthService authService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public ResponseAccessToken generateToken(EmailLoginRequestDto request) {
        User user = authService.getUser(request.getEmail());
        if (user != null) {
            if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                return ResponseAccessToken.builder()
                        .accessToken(jwtService.generateToken(user.getEmail()))
                        .build();
            }
        }
        return ResponseAccessToken.builder().error("이메일 또는 비밀번호가 잘못되었습니다.").build();
    }
}
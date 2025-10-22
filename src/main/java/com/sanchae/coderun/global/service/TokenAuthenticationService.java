package com.sanchae.coderun.global.service;

import com.sanchae.coderun.domain.auth.dto.EmailLoginRequestDto;
import com.sanchae.coderun.global.dto.ResponseAccessToken;
import com.sanchae.coderun.domain.auth.service.AuthService;
import com.sanchae.coderun.domain.user.entity.User;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
                // User 엔티티를 직접 전달
                ResponseAccessToken tokens = jwtService.getAccessTokenByUsername(user);
                tokens.setRole(user.getRole());
                return tokens;
            }
        }
        return ResponseAccessToken.builder()
                .error("이메일 또는 비밀번호가 잘못되었습니다.")
                .build();
    }

    public ResponseAccessToken reissueToken(String refreshToken) {
        try {
            return jwtService.getAccessTokenByRefreshToken(refreshToken);
        } catch (JwtException | UsernameNotFoundException e) {
            return ResponseAccessToken.builder()
                    .error("Refresh token invalid or expired.")
                    .build();
        }
    }
}
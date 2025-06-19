package com.sanchae.coderun.auth.service;

import com.sanchae.coderun.auth.dto.EmailLoginRequestDto;
import com.sanchae.coderun.auth.jwt.dto.ResponseAccessToken;
import com.sanchae.coderun.auth.jwt.service.TokenAuthenticationService;
import com.sanchae.coderun.user.repository.UserRepository;
import com.sanchae.coderun.user.dto.user.UserResponseDto;
import com.sanchae.coderun.user.dto.user.UserSignupRequestDto;
import com.sanchae.coderun.user.entity.Role;
import com.sanchae.coderun.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDto signUp(UserSignupRequestDto userSignupRequestDto) {
        String username = userSignupRequestDto.getUsername();
        String email = userSignupRequestDto.getEmail();
        String password = userSignupRequestDto.getPassword();

        Boolean isExist = userRepository.existsByUsername(username);



        if (isExist) {
            return new UserResponseDto(false, null);
        }

        // 중복되지 않으므로 데이터베이스에 저장

        User user = User.builder()
                .username(username)
                .email(email)
                .password(passwordEncoder.encode(password))
                .role(Role.BASIC)
                .build();

        User savedUser = userRepository.save(user);
        return new UserResponseDto(true, savedUser.getEmail());
    }

    public User getUser(String email) {
        return userRepository.findByEmail(email);
    }
}

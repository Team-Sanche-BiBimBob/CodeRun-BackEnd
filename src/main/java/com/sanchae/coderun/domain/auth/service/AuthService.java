package com.sanchae.coderun.domain.auth.service;

import com.sanchae.coderun.domain.user.dto.user.UserResponseDto;
import com.sanchae.coderun.domain.user.dto.user.UserSignupRequestDto;
import com.sanchae.coderun.domain.user.entity.Role;
import com.sanchae.coderun.domain.user.entity.User;
import com.sanchae.coderun.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDto signUp(UserSignupRequestDto userSignupRequestDto) {
        String email = userSignupRequestDto.getEmail();
        String password = userSignupRequestDto.getPassword();

        User user = User.builder()
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

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}

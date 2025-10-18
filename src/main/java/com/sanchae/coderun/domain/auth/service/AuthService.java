package com.sanchae.coderun.domain.auth.service;

import com.sanchae.coderun.domain.user.dto.user.UserResponseDto;
import com.sanchae.coderun.domain.user.dto.user.UserSignupRequestDto;
import com.sanchae.coderun.domain.user.entity.Role;
import com.sanchae.coderun.domain.user.entity.User;
import com.sanchae.coderun.domain.user.entity.UserProfile;
import com.sanchae.coderun.domain.user.repository.UserProfileRepository;
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
    private final UserProfileRepository userProfileRepository;

    public UserResponseDto signUp(UserSignupRequestDto userSignupRequestDto) {
        String email = userSignupRequestDto.getEmail();
        String password = userSignupRequestDto.getPassword();


        User user = User.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .username(userSignupRequestDto.getUsername())
                .role(Role.BASIC)
                .build();

        User firstSavedUser = userRepository.save(user);

        UserProfile userProfile = UserProfile.builder()
                .userId(firstSavedUser.getId())
                .build();

        userProfileRepository.save(userProfile);

        User savedUser = User.builder()
                .id(firstSavedUser.getId())
                .email(email)
                .password(passwordEncoder.encode(password))
                .username(userSignupRequestDto.getUsername())
                .role(Role.BASIC)
                .userProfile(userProfile)
                .build();

        userRepository.save(savedUser);

        return new UserResponseDto(true, savedUser.getEmail());
    }

    public User getUser(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}

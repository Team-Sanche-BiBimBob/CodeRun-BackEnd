package com.sanchae.coderun.domain.auth.controller;

import com.sanchae.coderun.domain.auth.dto.EmailLoginRequestDto;
import com.sanchae.coderun.domain.auth.service.AuthService;
import com.sanchae.coderun.domain.user.dto.user.UserResponseDto;
import com.sanchae.coderun.domain.user.dto.user.UserSignupRequestDto;
import com.sanchae.coderun.domain.user.entity.User;
import com.sanchae.coderun.global.dto.ResponseAccessToken;
import com.sanchae.coderun.global.service.TokenAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final TokenAuthenticationService tokenAuthenticationService;
    private final AuthService authService;

    @PostMapping("/signup")
    public UserResponseDto signup(@RequestBody UserSignupRequestDto userSignupRequestDto) {
        return authService.signUp(userSignupRequestDto);
    }

    @PostMapping("/signin")
    public ResponseEntity<ResponseAccessToken> signin(@RequestBody EmailLoginRequestDto emailLoginRequestDto) {
        ResponseAccessToken res = tokenAuthenticationService.generateToken(emailLoginRequestDto);
        if (res.getError() != null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
        }
        return ResponseEntity.ok(res);
    }

    @PostMapping("/reissue")
    public ResponseEntity<ResponseAccessToken> reissue() {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(null);
    }

    @PostMapping("/signout")
    public ResponseEntity<Void> signout() {
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/withdraw")
    public ResponseEntity<Void> withdraw() {
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/user/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable("id") String id,
                                                      @RequestBody(required = false) Object updateRequest) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(null);
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable("id") String id) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(null);
    }

    @GetMapping("/user")
    public List<User> getAllUsers() {
        return authService.getAllUsers();
    }
}
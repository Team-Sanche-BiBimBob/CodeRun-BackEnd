package com.sanchae.coderun.domain.auth.controller;

import com.sanchae.coderun.domain.auth.dto.EmailLoginRequestDto;

import com.sanchae.coderun.domain.auth.service.AuthService;
import com.sanchae.coderun.domain.user.dto.user.UserResponseDto;
import com.sanchae.coderun.domain.user.dto.user.UserSignupRequestDto;
import com.sanchae.coderun.domain.user.entity.User;
import com.sanchae.coderun.global.dto.ResponseAccessToken;
import com.sanchae.coderun.global.service.TokenAuthenticationService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Auth API", description = "Auth 기능을 구현한 API 입니다.")
@OpenAPIDefinition(servers = {@Server(url = "https://api.coderun.site/api")})
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
            return new ResponseEntity<>(res, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/reissue")
    public String reissue() {
        return "u reissued";

    }
    @PostMapping("/signout")
    public String signout() {
        return "u signed out";
    }

    @DeleteMapping("/withdraw")
    public String withdraw() {
        return "withdraw";
    }

    @PatchMapping("/user/{id}")
    public String updateUser(@PathVariable("id") String id) {
        return "updateuser";
    }

    @GetMapping("/user/{id}")
    public String getUser(@PathVariable("id") String id) {
        return "getuser";
    }

    @GetMapping("/user")
    public List<User> getAllUsers() {
        return authService.getAllUsers();
    }
}

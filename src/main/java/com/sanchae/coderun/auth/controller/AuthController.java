package com.sanchae.coderun.auth.controller;

import com.sanchae.coderun.auth.dto.EmailLoginRequestDto;
import com.sanchae.coderun.auth.jwt.dto.ResponseAccessToken;
import com.sanchae.coderun.auth.jwt.service.TokenAuthenticationService;
import com.sanchae.coderun.auth.service.AuthService;
import com.sanchae.coderun.user.dto.user.UserResponseDto;
import com.sanchae.coderun.user.dto.user.UserSignupRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
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
}

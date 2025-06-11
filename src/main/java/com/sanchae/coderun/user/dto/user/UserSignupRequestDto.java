package com.sanchae.coderun.user.dto.user;

import lombok.Data;

@Data
public class UserSignupRequestDto {
    private String username;
    private String password;
    private String email;

    public UserSignupRequestDto() {}

    public UserSignupRequestDto(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}

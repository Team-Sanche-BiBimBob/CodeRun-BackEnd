package com.sanchae.coderun.domain.user.dto.user;

import lombok.Data;

@Data
public class UserSignupRequestDto {
    private String password;
    private String email;

    public UserSignupRequestDto() {}

    public UserSignupRequestDto(String password, String email) {
        this.password = password;
        this.email = email;
    }
}

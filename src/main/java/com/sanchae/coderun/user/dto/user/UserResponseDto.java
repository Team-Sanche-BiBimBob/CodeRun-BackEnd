package com.sanchae.coderun.user.dto.user;

import lombok.Data;

@Data
public class UserResponseDto {
    private boolean isSuccess;
    private String email;

    public UserResponseDto(boolean isSuccess, String email) {
        this.isSuccess = isSuccess;
        this.email = email;
    }
}
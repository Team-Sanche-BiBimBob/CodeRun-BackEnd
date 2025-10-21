package com.sanchae.coderun.domain.user.dto.user;

import lombok.Data;

@Data
public class UserResponseDto {
    private boolean isSuccess;
    private Long id;
    private String email;

    public UserResponseDto(Long id, boolean isSuccess, String email) {
        this.id = id;
        this.isSuccess = isSuccess;
        this.email = email;
    }
}
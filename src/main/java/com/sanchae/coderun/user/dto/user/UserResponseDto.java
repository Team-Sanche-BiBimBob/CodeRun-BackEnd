package com.sanchae.coderun.user.dto.user;

import lombok.Data;

@Data
public class UserResponseDto {
    private Long id;
    private String username;
    private String email;

    public UserResponseDto() {}

    public UserResponseDto(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }
}
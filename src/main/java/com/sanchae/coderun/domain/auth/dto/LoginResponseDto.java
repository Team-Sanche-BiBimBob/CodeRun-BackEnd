package com.sanchae.coderun.domain.auth.dto;

import com.sanchae.coderun.domain.user.entity.Role;
import lombok.Data;

@Data
public class LoginResponseDto {
    private String accessToken;
    private Role role;
}

package com.sanchae.coderun.global.dto;

import com.sanchae.coderun.domain.user.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseAccessToken {
    private String accessToken;
    private Role role;
    private String error;
}

package com.sanchae.coderun.domain.auth.dto;

import lombok.Data;

@Data
public class EmailLoginRequestDto {
    private String email;
    private String password;
}

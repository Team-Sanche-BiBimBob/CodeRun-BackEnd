package com.sanchae.coderun.auth.dto;

import lombok.Data;

@Data
public class EmailLoginRequestDto {
    private String email;
    private String password;
}

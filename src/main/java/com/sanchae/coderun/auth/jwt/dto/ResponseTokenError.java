package com.sanchae.coderun.auth.jwt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseTokenError {
    private String error;
}

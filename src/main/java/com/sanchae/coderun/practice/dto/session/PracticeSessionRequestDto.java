package com.sanchae.coderun.practice.dto.session;

import lombok.Data;

@Data
public class PracticeSessionRequestDto {
    private Long practiceId;
    private String status;
}
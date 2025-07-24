package com.sanchae.coderun.domain.practice.dto.session;

import lombok.Data;

@Data
public class PracticeSessionRequestDto {
    private Long practiceId;
    private String status;
}
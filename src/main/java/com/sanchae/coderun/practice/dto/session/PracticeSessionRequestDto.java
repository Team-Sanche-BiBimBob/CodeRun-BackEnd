package com.sanchae.coderun.practice.dto.session;

import com.sanchae.coderun.practice.entity.PracticeStatus;
import lombok.Data;

@Data
public class PracticeSessionRequestDto {
    private Long practiceId;
    private PracticeStatus status;
}
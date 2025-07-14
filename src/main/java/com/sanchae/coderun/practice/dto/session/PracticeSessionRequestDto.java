package com.sanchae.coderun.practice.dto.session;

import com.sanchae.coderun.practice.entity.PracticeStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PracticeSessionRequestDto {
    private Long practiceId;
    private LocalDateTime startTime;
    private PracticeStatus status;
    private int typesPerMinute;
    private float correctRate;
}

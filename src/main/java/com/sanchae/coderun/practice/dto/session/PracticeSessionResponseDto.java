package com.sanchae.coderun.practice.dto.session;

import com.sanchae.coderun.practice.entity.PracticeStatus;
import lombok.Data;

@Data
public class PracticeSessionResponseDto {
    private Long id;
    private Long userId;
    private Long practiceId;
    private PracticeStatus status;

    public PracticeSessionResponseDto() {}

    public PracticeSessionResponseDto(Long id, Long userId, Long practiceId, PracticeStatus status) {
        this.id = id;
        this.userId = userId;
        this.practiceId = practiceId;
        this.status = status;
    }
}
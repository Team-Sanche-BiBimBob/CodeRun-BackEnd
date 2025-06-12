package com.sanchae.coderun.practice.dto.session;

import lombok.Data;

@Data
public class PracticeSessionRequestDto {
    private Long userId;
    private Long practiceId;
    private String status;  // 예: "IN_PROGRESS", "COMPLETED"

    public PracticeSessionRequestDto() {}

    public PracticeSessionRequestDto(Long userId, Long practiceId, String status) {
        this.userId = userId;
        this.practiceId = practiceId;
        this.status = status;
    }
}

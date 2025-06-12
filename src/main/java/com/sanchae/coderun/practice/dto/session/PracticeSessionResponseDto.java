package com.sanchae.coderun.practice.dto.session;

import lombok.Data;

@Data
public class PracticeSessionResponseDto {
    private Long id;
    private Long userId;
    private Long practiceId;
    private String status;

    public PracticeSessionResponseDto() {}

    public PracticeSessionResponseDto(Long id, Long userId, Long practiceId, String status) {
        this.id = id;
        this.userId = userId;
        this.practiceId = practiceId;
        this.status = status;
    }
}
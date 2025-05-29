package com.sanchae.coderun.dto.practicesession;

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

    public Long getUserId() {
        return userId;
    }

    public Long getPracticeId() {
        return practiceId;
    }

    public String getStatus() {
        return status;
    }
}

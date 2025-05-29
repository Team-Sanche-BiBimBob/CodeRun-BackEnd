package com.sanchae.coderun.dto.practicesession;

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

    public Long getId() {
        return id;
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
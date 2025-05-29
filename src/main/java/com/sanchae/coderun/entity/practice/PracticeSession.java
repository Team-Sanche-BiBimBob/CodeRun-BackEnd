package com.sanchae.coderun.entity.practice;

import jakarta.persistence.*;

@Entity
public class PracticeSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;  // 로그인 사용자의 ID (인증 없으면 생략 가능)

    @ManyToOne
    @JoinColumn(name = "practice_id")
    private Practice practice;

    private String status;  // 완료 여부 (ex: COMPLETE, IN_PROGRESS)

    public PracticeSession() {}

    public PracticeSession(Long userId, Practice practice, String status) {
        this.userId = userId;
        this.practice = practice;
        this.status = status;
    }

    public Long getId() { return id; }
    public Long getUserId() { return userId; }
    public Practice getPractice() { return practice; }
    public String getStatus() { return status; }
}
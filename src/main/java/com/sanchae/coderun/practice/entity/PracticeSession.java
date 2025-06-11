package com.sanchae.coderun.practice.entity;

import com.sanchae.coderun.user.entity.User;
import com.sanchae.coderun.language.entity.Language;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class PracticeSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;  // 로그인 사용자

    @ManyToOne
    private Language language;

    @ManyToOne
    @JoinColumn(name = "practice_id")
    private Practice practice;

    private String status;  // 완료 여부 (ex: COMPLETE, IN_PROGRESS)

    public PracticeSession() {}

    public PracticeSession(User user, Practice practice, String status) {
        this.user = user;
        this.practice = practice;
        this.status = status;
    }

}
package com.sanchae.coderun.practice.entity;

import com.sanchae.coderun.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PracticeSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;  // 로그인 사용자

    @ManyToOne
    private Practice practice;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private PracticeStatus status;  // 완료 여부 (ex: COMPLETE, IN_PROGRESS)

    private int typesPerMinute; // 분당 타수

    private float correctRate; // 정답률
}
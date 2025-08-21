package com.sanchae.coderun.domain.problem.entity;

import com.sanchae.coderun.domain.practice.entity.Practice;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Problem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne
    private Practice practice;

    @Column(columnDefinition = "TEXT")
    private String content; // 문제 내용

    private Long level;

    private ProblemType problemType;
}

package com.sanchae.coderun.problem.entity;

import com.sanchae.coderun.practice.entity.Practice;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Problem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Practice practice;

    private String content; // 문제 내용

    public Problem() {}

    public Problem(Practice practice) {
        this.practice = practice;
    }
}

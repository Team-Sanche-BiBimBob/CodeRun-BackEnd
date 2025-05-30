package com.sanchae.coderun.entity.problem;

import com.sanchae.coderun.entity.practice.Practice;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Problem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String level;

    @ManyToOne
    private Practice practice;

    public Problem() {}

    public Problem(String title, String description, String level, Practice practice) {
        this.title = title;
        this.description = description;
        this.level = level;
        this.practice = practice;
    }
}

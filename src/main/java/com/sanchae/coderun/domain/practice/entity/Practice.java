package com.sanchae.coderun.domain.practice.entity;


import com.sanchae.coderun.domain.language.entity.Language;
import com.sanchae.coderun.domain.problem.entity.Problem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Practice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String level;

    private PracticeType type;

    @ManyToOne
    private Language language;

    @OneToMany(mappedBy = "practice")
    private List<Problem> problems;
}
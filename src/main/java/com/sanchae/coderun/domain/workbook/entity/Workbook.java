package com.sanchae.coderun.domain.workbook.entity;

import com.sanchae.coderun.domain.language.entity.Language;
import com.sanchae.coderun.domain.practice.entity.PracticeType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Workbook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Language workbookLanguage;

    private PracticeType practiceType;

    private Long workbookProblemsCount;

    @OneToMany
    private List<WorkbookProblems> workbookProblems;
}

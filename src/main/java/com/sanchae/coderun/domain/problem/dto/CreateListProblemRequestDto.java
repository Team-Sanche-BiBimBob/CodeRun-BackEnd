package com.sanchae.coderun.domain.problem.dto;

import com.sanchae.coderun.domain.problem.entity.ProblemType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateListProblemRequestDto {
    private String title;
    private Long practiceId;
    private List<String> contents;
    private ProblemType problemType;
    private Long level;
    private Long languageId;
}

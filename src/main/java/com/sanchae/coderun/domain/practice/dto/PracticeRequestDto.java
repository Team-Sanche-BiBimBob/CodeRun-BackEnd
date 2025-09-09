package com.sanchae.coderun.domain.practice.dto;

import com.sanchae.coderun.domain.practice.entity.PracticeType;
import com.sanchae.coderun.domain.problem.dto.ProblemResponseDto;
import com.sanchae.coderun.domain.problem.entity.Problem;
import lombok.Data;

import java.util.List;

@Data
public class PracticeRequestDto {
    private String title;
    private String description;
    private String level;
    private Long languageId; // <- 수정됨
    private PracticeType type;
}
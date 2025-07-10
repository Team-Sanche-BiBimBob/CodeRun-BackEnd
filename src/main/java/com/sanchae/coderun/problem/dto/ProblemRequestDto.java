package com.sanchae.coderun.problem.dto;

import com.sanchae.coderun.problem.entity.ProblemType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProblemRequestDto {
    private Long practiceId;
    private String title;
    private Long level;
    private String language;
    private String url;
    private ProblemType problemType;
    private String content;
}
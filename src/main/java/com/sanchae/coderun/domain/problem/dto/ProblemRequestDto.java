package com.sanchae.coderun.domain.problem.dto;

import com.sanchae.coderun.domain.problem.entity.ProblemType;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProblemRequestDto {
    private Long practiceId;
    private String title;
    private Long level;
    private Long languageId;
    private String url;
    private ProblemType problemType;
    private String content;
}
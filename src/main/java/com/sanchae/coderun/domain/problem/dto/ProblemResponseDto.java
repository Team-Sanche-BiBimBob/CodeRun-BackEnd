package com.sanchae.coderun.domain.problem.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProblemResponseDto {
    private Long id;
    private Long practiceId;
    private String title;
    private Boolean isSuccess;
}
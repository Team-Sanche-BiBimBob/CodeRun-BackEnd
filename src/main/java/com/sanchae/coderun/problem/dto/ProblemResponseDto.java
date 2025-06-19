package com.sanchae.coderun.problem.dto;

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
    private String title;
    private String description;
    private String level;
    private String language;
}
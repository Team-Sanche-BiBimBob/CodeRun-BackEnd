package com.sanchae.coderun.problem.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProblemRequestDto {
    private String title;
    private String description;
    private String level;
    private String language;
}
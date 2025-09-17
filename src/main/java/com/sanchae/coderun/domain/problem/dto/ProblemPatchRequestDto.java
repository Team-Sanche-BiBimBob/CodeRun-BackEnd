package com.sanchae.coderun.domain.problem.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProblemPatchRequestDto {
    private String title;
    private String url;
    private String content;
}

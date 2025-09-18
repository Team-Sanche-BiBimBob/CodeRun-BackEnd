package com.sanchae.coderun.domain.problem.dto;

import com.sanchae.coderun.domain.problem.entity.Problem;
import com.sanchae.coderun.domain.problem.entity.ProblemType;
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
    private ProblemType problemType;
    private Boolean isSuccess;

    public static ProblemResponseDto fromEntity(Problem problem){
        return ProblemResponseDto.builder()
                .id(problem.getId())
                .practiceId(problem.getPractice().getId())
                .title(problem.getTitle())
                .problemType(problem.getProblemType())
                .isSuccess(true)
                .build();

    }
}
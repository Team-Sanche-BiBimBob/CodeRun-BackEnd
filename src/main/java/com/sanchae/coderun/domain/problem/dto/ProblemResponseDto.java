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
    private String content;
    private Boolean isSuccess;

    public static ProblemResponseDto fromEntity(Problem problem){
        ProblemResponseDto problemResponseDto = ProblemResponseDto.builder()
                .id(problem.getId())
                .practiceId(problem.getPractice().getId())
                .title(problem.getTitle())
                .problemType(problem.getProblemType())
                .content(problem.getContent())
                .isSuccess(true)
                .build();

        if (problemResponseDto.practiceId == null) {
            throw new RuntimeException("practiceId가 null입니다.");
        }

        return problemResponseDto;
    }
}
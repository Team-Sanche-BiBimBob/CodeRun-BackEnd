package com.sanchae.coderun.domain.classes.dto;

import com.sanchae.coderun.domain.problem.entity.Problem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProblemsInFolderResponseDto {
    private List<Problem> problems;
}

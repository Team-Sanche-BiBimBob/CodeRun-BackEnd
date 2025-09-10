package com.sanchae.coderun.domain.problem.service;

import com.sanchae.coderun.domain.problem.dto.ProblemRequestDto;
import com.sanchae.coderun.domain.problem.dto.ProblemResponseDto;
import com.sanchae.coderun.domain.problem.entity.Problem;

import java.util.List;

public interface ProblemService {
    List<Problem> findAllProblems();
    List<ProblemResponseDto> findAllProblemsByPracticeId(Long practiceId);
    Problem findProblemById(Long id);
    ProblemResponseDto createProblem(ProblemRequestDto problemRequestDto);
    ProblemResponseDto updateProblem(Long id, ProblemRequestDto problemRequestDto);
    void deleteProblem(Long id);
}

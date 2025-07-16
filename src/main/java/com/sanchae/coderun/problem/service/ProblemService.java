package com.sanchae.coderun.problem.service;

import com.sanchae.coderun.problem.dto.ProblemRequestDto;
import com.sanchae.coderun.problem.dto.ProblemResponseDto;
import com.sanchae.coderun.problem.entity.Problem;

import java.util.List;

public interface ProblemService {
    List<Problem> findAllProblems();
    Problem findProblemById(Long id);
    ProblemResponseDto createProblem(ProblemRequestDto problemRequestDto);
    ProblemResponseDto updateProblem(Long id, ProblemRequestDto problemRequestDto);
    void deleteProblem(Long id);
}

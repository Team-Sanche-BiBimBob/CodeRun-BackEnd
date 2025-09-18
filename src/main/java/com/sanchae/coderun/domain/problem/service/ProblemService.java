package com.sanchae.coderun.domain.problem.service;

import com.sanchae.coderun.domain.problem.dto.ProblemPatchRequestDto;
import com.sanchae.coderun.domain.problem.dto.ProblemRequestDto;
import com.sanchae.coderun.domain.problem.dto.ProblemResponseDto;
import com.sanchae.coderun.domain.problem.entity.Problem;

import java.util.List;

public interface ProblemService {
    List<ProblemResponseDto> findAllProblems();
    List<ProblemResponseDto> findAllProblemsByPracticeId(Long practiceId);
    ProblemResponseDto findProblemById(Long id);
    ProblemResponseDto createProblem(ProblemRequestDto problemRequestDto);
    ProblemResponseDto updateProblem(Long id, ProblemPatchRequestDto problemRequestDto);
    void deleteProblem(Long id);

    List<ProblemResponseDto> findWordProblems(ProblemRequestDto problemRequestDto);
    List<ProblemResponseDto> findSentenceProblems(ProblemResponseDto problemResponseDto);
    List<ProblemResponseDto> findFullCodeProblems(ProblemResponseDto problemResponseDto);
}

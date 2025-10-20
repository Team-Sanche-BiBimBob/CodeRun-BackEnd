package com.sanchae.coderun.domain.problem.service;

import com.sanchae.coderun.domain.problem.dto.CreateListProblemRequestDto;
import com.sanchae.coderun.domain.problem.dto.ProblemPatchRequestDto;
import com.sanchae.coderun.domain.problem.dto.ProblemRequestDto;
import com.sanchae.coderun.domain.problem.dto.ProblemResponseDto;

import java.util.List;

public interface ProblemService {
    List<ProblemResponseDto> findAllProblems();
    List<ProblemResponseDto> findAllProblemsByPracticeId(Long practiceId);
    ProblemResponseDto findProblemById(Long id);
    ProblemResponseDto createProblem(ProblemRequestDto problemRequestDto);
    ProblemResponseDto updateProblem(Long id, ProblemPatchRequestDto problemRequestDto);
    List<ProblemResponseDto> createProblemsWithList(CreateListProblemRequestDto problems);
    void deleteProblem(Long id);

    List<ProblemResponseDto> findWordProblems(Long languageId);
    List<ProblemResponseDto> findSentenceProblems(Long languageId);
    List<ProblemResponseDto> findFullCodeProblems(Long languageId);
}

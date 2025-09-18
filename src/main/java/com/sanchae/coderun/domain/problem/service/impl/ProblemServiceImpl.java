package com.sanchae.coderun.domain.problem.service.impl;

import com.sanchae.coderun.domain.practice.entity.Practice;
import com.sanchae.coderun.domain.practice.repository.PracticeRepository;
import com.sanchae.coderun.domain.problem.dto.ProblemPatchRequestDto;
import com.sanchae.coderun.domain.problem.dto.ProblemRequestDto;
import com.sanchae.coderun.domain.problem.dto.ProblemResponseDto;
import com.sanchae.coderun.domain.problem.entity.Problem;
import com.sanchae.coderun.domain.problem.entity.ProblemType;
import com.sanchae.coderun.domain.problem.repository.ProblemRepository;
import com.sanchae.coderun.domain.problem.service.ProblemService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
// import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProblemServiceImpl implements ProblemService {

    private final ProblemRepository problemRepository;
    private final PracticeRepository practiceRepository;

    @Override
    public List<ProblemResponseDto> findAllProblems() {
        return problemRepository.findAll().stream()
                .map(ProblemResponseDto::fromEntity)
                .toList();
    }

    @Override
    public List<ProblemResponseDto> findAllProblemsByPracticeId(Long practiceId) {
        // entity가 아니라 Dto를 반환하도록 수정
        return problemRepository.findAllByPractice_id(practiceId).stream()
                .map(ProblemResponseDto::fromEntity)
                .toList();

    }

    @Override
    public ProblemResponseDto findProblemById(Long id) {
        return problemRepository.findById(id)
                .map(ProblemResponseDto::fromEntity)
                .orElse(null);
    }

    @Transactional
    @Override
    public ProblemResponseDto createProblem(ProblemRequestDto problemRequestDto) {

        Practice practice = practiceRepository.findById(problemRequestDto.getPracticeId()).orElse(null);
        if (practice == null) { return null; }

        Problem problem = Problem.builder()
                .title(problemRequestDto.getTitle())
                .content(problemRequestDto.getContent())
                .problemType(problemRequestDto.getProblemType())
                .practice(practice)
                .level(3L)
                .build();

        problemRepository.save(problem);

        return ProblemResponseDto.builder()
                .id(problem.getId())
                .title(problem.getTitle())
                .practiceId(problem.getPractice().getId())
                .problemType(problem.getProblemType())
                .isSuccess(true)
                .build();
    }

    @Override
    public ProblemResponseDto updateProblem(Long id, ProblemPatchRequestDto problemRequestDto) {

        Problem problem = problemRepository.findById(id).orElseThrow(() -> new RuntimeException());

        Problem updated = problem.toBuilder()
                .id(problem.getId())
                .title(problemRequestDto.getTitle())
                .content(problemRequestDto.getContent())
                .problemType(problem.getProblemType())
                .level(problem.getLevel())
                .practice(problem.getPractice())
                .build();

        problemRepository.save(updated);

        return ProblemResponseDto.builder()
                .id(updated.getId())
                .title(updated.getTitle())
                .practiceId(updated.getPractice().getId())
                .problemType(updated.getProblemType())
                .isSuccess(true)
                .build();
    }

    @Override
    public void deleteProblem(Long id) {
        problemRepository.deleteById(id);
    }

    @Override
    public List<ProblemResponseDto> findWordProblems(ProblemRequestDto problemRequestDto) {
        return findAllProblems().stream()
                .filter(filteredDtos -> filteredDtos.getProblemType() == ProblemType.PROBLEM_WORD)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProblemResponseDto> findSentenceProblems(ProblemResponseDto problemResponseDto) {
        return findAllProblems().stream()
                .filter(filteredDtos -> filteredDtos.getProblemType() == ProblemType.PROBLEM_SENTENCE)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProblemResponseDto> findFullCodeProblems(ProblemResponseDto problemResponseDto) {
        return findAllProblems().stream()
                .filter(filteredDtos -> filteredDtos.getProblemType() == ProblemType.PROBLEM_FULL_CODE)
                .collect(Collectors.toList());
    }
}

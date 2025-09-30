package com.sanchae.coderun.domain.problem.service.impl;

import com.sanchae.coderun.domain.language.entity.Language;
import com.sanchae.coderun.domain.language.repository.LanguageRepository;
import com.sanchae.coderun.domain.practice.entity.Practice;
import com.sanchae.coderun.domain.practice.repository.PracticeRepository;
import com.sanchae.coderun.domain.problem.dto.CreateListProblemRequestDto;
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
    private final LanguageRepository languageRepository;

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
        Problem problem = problemRepository.findById(id).orElseThrow(() -> new RuntimeException());
        return ProblemResponseDto.fromEntity(problem);
    }

    @Transactional
    @Override
    public ProblemResponseDto createProblem(ProblemRequestDto problemRequestDto) {

        Practice practice = practiceRepository.findById(problemRequestDto.getPracticeId()).orElse(null);
        Language language = languageRepository.findById(problemRequestDto.getLanguageId()).orElse(null);

        if (practice == null || language == null) { return null; }

        Problem problem = Problem.builder()
                .title(problemRequestDto.getTitle())
                .content(problemRequestDto.getContent())
                .problemType(problemRequestDto.getProblemType())
                .practice(practice)
                .language(language)
                .level(3L)
                .build();

        problemRepository.save(problem);

        return ProblemResponseDto.builder()
                .id(problem.getId())
                .title(problem.getTitle())
                .practiceId(problem.getPractice().getId())
                .content(problemRequestDto.getContent())
                .problemType(problem.getProblemType())
                .language(language)
                .isSuccess(true)
                .build();
    }

    @Override
    public List<ProblemResponseDto> createProblemsWithList(CreateListProblemRequestDto problems) {
        Practice practice = practiceRepository.findById(problems.getPracticeId()).orElse(null);
        Language language = languageRepository.findById(problems.getLanguageId()).orElse(null);

        if (practice == null || language == null) { return null; }

        int listLength = problems.getContents().size();
        List<Problem> problemList = new ArrayList<>();
        List<ProblemResponseDto> problemResponseDtoList = new ArrayList<>();
        Problem problem;

        for (int i = 0; i < listLength; i++) {
            problem = Problem.builder()
                    .practice(practice)
                    .title(problems.getContents().get(i))
                    .content(problems.getContents().get(i))
                    .level(problems.getLevel())
                    .problemType(problems.getProblemType())
                    .language(language)
                    .build();

            problemList.add(problem);
        }

        List<Problem> savedProblem = problemRepository.saveAll(problemList);

        for (int i = 0; i < listLength; i++) {
            ProblemResponseDto problemResponse = ProblemResponseDto.builder()
                    .id(savedProblem.get(i).getId())
                    .title(savedProblem.get(i).getTitle())
                    .practiceId(savedProblem.get(i).getPractice().getId())
                    .content(savedProblem.get(i).getContent())
                    .problemType(savedProblem.get(i).getProblemType())
                    .language(language)
                    .isSuccess(true)
                    .build();

            problemResponseDtoList.add(problemResponse);
        }

        return problemResponseDtoList;
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
                .language(problem.getLanguage())
                .build();

        problemRepository.save(updated);

        return ProblemResponseDto.builder()
                .id(updated.getId())
                .title(updated.getTitle())
                .content(updated.getContent())
                .practiceId(updated.getPractice().getId())
                .problemType(updated.getProblemType())
                .language(updated.getLanguage())
                .isSuccess(true)
                .build();
    }

    @Override
    public void deleteProblem(Long id) {
        problemRepository.deleteById(id);
    }

    @Override
    public List<ProblemResponseDto> findWordProblems() {
        return findAllProblems().stream()
                .filter(filteredDtos -> filteredDtos.getProblemType() == ProblemType.PROBLEM_WORD)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProblemResponseDto> findSentenceProblems() {
        return findAllProblems().stream()
                .filter(filteredDtos -> filteredDtos.getProblemType() == ProblemType.PROBLEM_SENTENCE)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProblemResponseDto> findFullCodeProblems() {
        return findAllProblems().stream()
                .filter(filteredDtos -> filteredDtos.getProblemType() == ProblemType.PROBLEM_FULL_CODE)
                .collect(Collectors.toList());
    }
}

package com.sanchae.coderun.problem.service.impl;

import com.sanchae.coderun.practice.entity.Practice;
import com.sanchae.coderun.practice.repository.PracticeRepository;
import com.sanchae.coderun.problem.dto.ProblemRequestDto;
import com.sanchae.coderun.problem.dto.ProblemResponseDto;
import com.sanchae.coderun.problem.entity.Problem;
import com.sanchae.coderun.problem.repository.ProblemRepository;
import com.sanchae.coderun.problem.service.ProblemService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
// import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProblemServiceImpl implements ProblemService {

    private final ProblemRepository problemRepository;
    private final PracticeRepository practiceRepository;
    // private final RestTemplate restTemplate;

    @Override
    public List<Problem> findAllProblems() {
        return problemRepository.findAll();
    }

    @Override
    public Problem findProblemById(Long id) {
        return problemRepository.findById(id).orElseThrow(() -> new RuntimeException("찾으시려는 게시글이 존재하지 않습니다."));
    }

    @Transactional
    @Override
    public ProblemResponseDto createProblem(ProblemRequestDto problemRequestDto) {
        /*String url = problemRequestDto.getUrl();
        log.info("URL: {}", problemRequestDto.getProblemType());
        log.info("URL: {}", problemRequestDto.getLanguage());
        log.info("URL: {}", problemRequestDto.getTitle());
        log.info("URL: {}", problemRequestDto.getLevel());
        log.info("URL: {}", url);
        System.out.println(url);
        ResponseEntity<?> content = restTemplate.getForEntity(url, String.class);*/

        Practice practice = practiceRepository.findById(problemRequestDto.getPracticeId()).orElse(null);
        if (practice == null) { return null; }

        Problem savedProblem = problemRepository.save(Problem
                .builder()
                .title(problemRequestDto.getTitle())
                .content(problemRequestDto.getContent())
                .problemType(problemRequestDto.getProblemType())
                .practice(practice)
                .level(3L)
                .build()
        );

        return ProblemResponseDto.builder()
                .id(savedProblem.getId())
                .title(problemRequestDto.getTitle())
                .isSuccess(true)
                .build();
    }

    @Override
    public ProblemResponseDto updateProblem(Long id, ProblemRequestDto problemRequestDto) {
//
//        String url = problemRequestDto.getUrl();
//
//        ResponseEntity<?> content = restTemplate.getForEntity(url, String.class);

        Problem problem = problemRepository.findById(id).orElse(null);
        Practice practice = practiceRepository.findById(problemRequestDto.getPracticeId()).orElse(null);

        if (problem == null || practice == null) { return null; }

        Problem newProblem = Problem.builder()
                .id(problem.getId())
                .title(problemRequestDto.getTitle())
                .content(problemRequestDto.getContent())
                .problemType(problemRequestDto.getProblemType())
                .level(problemRequestDto.getLevel())
                .practice(practice)
                .build();

        problemRepository.save(newProblem);

        return ProblemResponseDto.builder()
                .id(newProblem.getId())
                .title(newProblem.getTitle())
                .isSuccess(true)
                .build();
    }

    @Override
    public void deleteProblem(Long id) {
        Problem problem = findProblemById(id);
        problemRepository.delete(problem);
    }
}

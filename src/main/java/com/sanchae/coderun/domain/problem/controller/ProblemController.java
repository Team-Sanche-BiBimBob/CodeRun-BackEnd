package com.sanchae.coderun.domain.problem.controller;

import com.sanchae.coderun.domain.problem.dto.CreateListProblemRequestDto;
import com.sanchae.coderun.domain.problem.dto.ProblemPatchRequestDto;
import com.sanchae.coderun.domain.problem.dto.ProblemRequestDto;
import com.sanchae.coderun.domain.problem.dto.ProblemResponseDto;
import com.sanchae.coderun.domain.problem.service.ProblemService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/problems")
@RequiredArgsConstructor
@Tag(name = "문제 목록 API", description = "문제 CRUD 기능을 구현한 API 입니다.")
public class ProblemController {

    private final ProblemService problemService;

    @GetMapping("") // 이거 왜 안 나타나냐
    @Transactional()
    public List<ProblemResponseDto> getAllProblems() {
        return problemService.findAllProblems();
    }

    @GetMapping("/{problemId}")
    public ProblemResponseDto getProblems(@PathVariable Long problemId) {
        return problemService.findProblemById(problemId);
    }

    @GetMapping("/words/{languageId}")
    public List<ProblemResponseDto> findWordProblemsByLanguage(@PathVariable Long languageId) {
        return problemService.findWordProblems(languageId);
    }

    @GetMapping("/sentences/{languageId}")
    public List<ProblemResponseDto> findSentenceProblemsByLanguage(@PathVariable Long languageId) {
        return problemService.findSentenceProblems(languageId);
    }

    @GetMapping("/full-code/{languageId}")
    public List<ProblemResponseDto> findFullCodeProblemsByLanguage(@PathVariable Long languageId) {
        return problemService.findFullCodeProblems(languageId);
    }

    @PostMapping()
    public ProblemResponseDto createProblem(@RequestBody ProblemRequestDto problemRequestDto) {
        return problemService.createProblem(problemRequestDto);
    }

    @PostMapping("/lists")
    public List<ProblemResponseDto> createProblemByList(@RequestBody CreateListProblemRequestDto problemRequestDto) {
        return problemService.createProblemsWithList(problemRequestDto);
    }

    @PatchMapping("/{problemId}")
    public ProblemResponseDto updateProblem(@PathVariable Long problemId, @RequestBody ProblemPatchRequestDto problemRequestDto) {
        log.info("entered Controller");
        return problemService.updateProblem(problemId, problemRequestDto);
    }

    @DeleteMapping("/{problemId}")
    public void deleteProblem(@PathVariable Long problemId) {
        problemService.deleteProblem(problemId);
    }
}

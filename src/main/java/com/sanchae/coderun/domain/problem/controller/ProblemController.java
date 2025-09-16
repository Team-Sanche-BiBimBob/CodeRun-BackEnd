package com.sanchae.coderun.domain.problem.controller;

import com.sanchae.coderun.domain.problem.dto.ProblemRequestDto;
import com.sanchae.coderun.domain.problem.dto.ProblemResponseDto;
import com.sanchae.coderun.domain.problem.entity.Problem;
import com.sanchae.coderun.domain.problem.service.impl.ProblemServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/problems")
@RequiredArgsConstructor
@Tag(name = "문제 목록 API", description = "문제 CRUD 기능을 구현한 API 입니다.")
public class ProblemController {

    private final ProblemServiceImpl problemService;

    @GetMapping("") // 이거 왜 안 나타나냐
    public List<Problem> getAllProblems() {
        return problemService.findAllProblems();
    }

    @GetMapping("/{problemId}")
    public Problem getProblems(@PathVariable Long problemId) {
        return problemService.findProblemById(problemId);
    }

    @PostMapping()
    public ProblemResponseDto createProblem(@RequestBody ProblemRequestDto problemRequestDto) {
        return problemService.createProblem(problemRequestDto);
    }

    @PatchMapping("/{problemId}")
    public ProblemResponseDto updateProblem(@PathVariable Long problemId, ProblemRequestDto problemRequestDto) {
        return problemService.updateProblem(problemId, problemRequestDto);
    }

    @DeleteMapping("/{problemId}")
    public void deleteProblem(@PathVariable Long problemId) {
        problemService.deleteProblem(problemId);
    }

}

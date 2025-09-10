package com.sanchae.coderun.domain.problem.controller;

import com.sanchae.coderun.domain.problem.dto.ProblemRequestDto;
import com.sanchae.coderun.domain.problem.entity.Problem;
import com.sanchae.coderun.domain.problem.service.impl.ProblemServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/problems")
@RequiredArgsConstructor
@Tag(name = "문제 목록 API", description = "문제 CRUD 기능을 구현한 API 입니다.")
public class ProblemController {

    private final ProblemServiceImpl problemService;

    @GetMapping()
    public List<Problem> getAllProblems() {
        return problemService.findAllProblems();
    }

    @GetMapping("/{problemId}")
    public Problem getProblems(@PathVariable Long problemId) {
        return problemService.findProblemById(problemId);
    }

    @PostMapping()
    public ResponseEntity<String> createProblem(@RequestBody ProblemRequestDto problemRequestDto) {
        problemService.createProblem(problemRequestDto);
        return ResponseEntity.ok().body("created problem");
    }

    @PatchMapping("/{problemId}")
    public ResponseEntity<String> updateProblem(@PathVariable Long problemId, ProblemRequestDto problemRequestDto) {
        problemService.updateProblem(problemId, problemRequestDto);
        return ResponseEntity.ok().body("updated problem");
    }

    @DeleteMapping("/{problemId}")
    public ResponseEntity<String> deleteProblem(@PathVariable Long problemId) {
        problemService.deleteProblem(problemId);
        return ResponseEntity.ok().body("deleted problem");
    }

}

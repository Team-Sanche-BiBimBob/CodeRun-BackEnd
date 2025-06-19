package com.sanchae.coderun.problem.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/problems")
public class ProblemController {

    @GetMapping()
    public String getAllProblems() {
        return "this is problem list";
    }

    @GetMapping("/{problemId}")
    public String getProblems(@PathVariable String problemId) {
        return "this is a problem";
    }

    @PostMapping()
    public String addProblem() {
        return "added problem";
    }

    @PatchMapping("/{problemId}")
    public String updateProblem(@PathVariable String problemId) {
        return "updated problem";
    }

    @DeleteMapping("/{problemId}")
    public String deleteProblem(@PathVariable String problemId) {
        return "deleted problem";
    }




}

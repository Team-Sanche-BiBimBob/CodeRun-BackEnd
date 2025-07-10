package com.sanchae.coderun.practice.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/practices")
public class PracticeController {

    @GetMapping("")
    public String getAllPractice() {
        return "this is practice list";
    }

    @GetMapping("/{practiceId}")
    public String getPracticeById(@PathVariable String practiceId) {
        return "this is practice ye";
    }

    @GetMapping("/search")
    public String searchPractice() {
        return "yeah u searched practice";
    }
}
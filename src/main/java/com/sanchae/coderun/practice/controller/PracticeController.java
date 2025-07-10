package com.sanchae.coderun.practice.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PracticeController {

    @GetMapping("/practices")
    public String getAllPractice() {
        return "this is practice list";
    }

    @GetMapping("/practices/{practiceId}")
    public String getPracticeById(@PathVariable String practiceId) {
        return "this is practice ye";
    }

    @GetMapping("/practices/search")
    public String searchPractice() {
        return "yeah u searched practice";
    }
}
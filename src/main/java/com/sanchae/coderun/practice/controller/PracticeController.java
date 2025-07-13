package com.sanchae.coderun.practice.controller;

import com.sanchae.coderun.practice.dto.PracticeRequestDto;
import com.sanchae.coderun.practice.dto.PracticeResponseDto;
import com.sanchae.coderun.practice.service.PracticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/practices")
@RequiredArgsConstructor
public class PracticeController {

    private final PracticeService practiceService;

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

    @PostMapping("")
    public PracticeResponseDto createPractice(@RequestBody PracticeRequestDto practiceRequestDto) {
        return practiceService.createPractice(practiceRequestDto);
    }
}
package com.sanchae.coderun.domain.practice.controller;

import com.sanchae.coderun.domain.practice.dto.PracticeRequestDto;
import com.sanchae.coderun.domain.practice.dto.PracticeResponseDto;
import com.sanchae.coderun.domain.practice.entity.PracticeType;
import com.sanchae.coderun.domain.practice.service.impl.PracticeServiceImpl;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/practices")
@RequiredArgsConstructor
@Tag(name = "연습 API", description = "연습 기능을 구현한 API 입니다.")
@OpenAPIDefinition(servers = {@Server(url = "https://api.coderun.site/api")})
public class PracticeController {
    private final PracticeServiceImpl practiceService;

    @GetMapping()
    public List<PracticeResponseDto> getAllPractice(
            @RequestParam(name="lang", required = false) Long langId,
            @RequestParam(name="type", required = false) PracticeType type) {

        if(langId == null && type == null){
            return practiceService.getAllPractice();
        }
        long lang = 1;
        PracticeType t = PracticeType.PRACTICE_WORD;
        if(langId != null){ lang = langId; }
        if(type != null ){t = type;}
        return practiceService.getAllPracticeByLanguageIdAndType(lang, t);
    }

    @GetMapping("/{practiceId}")
    public PracticeResponseDto getPracticeById(@PathVariable String practiceId) {
        return practiceService.getPracticeById(Long.parseLong(practiceId));
    }

    @GetMapping("/{practiceId}/problems")
    public PracticeResponseDto getPracticeProblems(@PathVariable String practiceId) {
        return practiceService.getPracticeById(Long.parseLong(practiceId));
    }

    @PostMapping("")
    public PracticeResponseDto createPractice(@RequestBody PracticeRequestDto requestDto) {
        return practiceService.createPractice(requestDto);
    }
}
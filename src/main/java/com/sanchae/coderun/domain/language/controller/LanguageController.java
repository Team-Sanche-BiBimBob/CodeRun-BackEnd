package com.sanchae.coderun.domain.language.controller;

import com.sanchae.coderun.domain.language.dto.LanguageRequestDto;
import com.sanchae.coderun.domain.language.dto.LanguageResponseDto;
import com.sanchae.coderun.domain.language.service.LanguageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/languages")
@RequiredArgsConstructor
@Tag(name = "언어 API", description = "언어 CRUD 기능을 구현한 API 입니다.")
public class LanguageController {

    private final LanguageService languageService;

    // POST /api/languages
    @PostMapping("")
    public ResponseEntity<String> saveLanguage(@RequestBody LanguageRequestDto requestDto) {
        try {
            languageService.saveLanguage(requestDto);
            return ResponseEntity.ok("언어 등록 완료");
        }catch(DataIntegrityViolationException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    // GET /api/languages
    @GetMapping("")
    public ResponseEntity<List<LanguageResponseDto>> getAllLanguages() {
        return ResponseEntity.ok(languageService.getAllLanguages());
    }
}
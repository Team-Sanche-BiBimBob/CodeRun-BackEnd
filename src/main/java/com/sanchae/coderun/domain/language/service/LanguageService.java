package com.sanchae.coderun.domain.language.service;

import com.sanchae.coderun.domain.language.dto.LanguageRequestDto;
import com.sanchae.coderun.domain.language.dto.LanguageResponseDto;

import java.util.List;

public interface LanguageService {
    void saveLanguage(LanguageRequestDto requestDto);
    void deleteLanguage(Long id);
    List<LanguageResponseDto> getAllLanguages();
}
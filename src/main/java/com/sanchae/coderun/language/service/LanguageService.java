package com.sanchae.coderun.language.service;

import com.sanchae.coderun.language.dto.LanguageRequestDto;
import com.sanchae.coderun.language.dto.LanguageResponseDto;

import java.util.List;

public interface LanguageService {
    void saveLanguage(LanguageRequestDto requestDto);
    List<LanguageResponseDto> getAllLanguages();
}
package com.sanchae.coderun.language.service.impl;

import com.sanchae.coderun.language.dto.LanguageRequestDto;
import com.sanchae.coderun.language.dto.LanguageResponseDto;
import com.sanchae.coderun.language.entity.Language;
import com.sanchae.coderun.language.repository.LanguageRepository;
import com.sanchae.coderun.language.service.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepository languageRepository;

    @Override
    public void saveLanguage(LanguageRequestDto requestDto) {
        Language language = Language.builder()
                .name(requestDto.getName())
                .version(requestDto.getVersion())
                .description(requestDto.getDescription())
                .build();

        languageRepository.save(language);
    }

    @Override
    public List<Language> getAllLanguages() {
        return languageRepository.findAll();
    }
}
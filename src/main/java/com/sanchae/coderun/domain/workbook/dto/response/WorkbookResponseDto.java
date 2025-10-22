package com.sanchae.coderun.domain.workbook.dto.response;

import com.sanchae.coderun.domain.practice.entity.PracticeType;

public record WorkbookResponseDto(
        Long id,
        Long languageId,
        String languageName,
        PracticeType practiceType,
        Integer problemCount,
        String title,
        String description
) {}
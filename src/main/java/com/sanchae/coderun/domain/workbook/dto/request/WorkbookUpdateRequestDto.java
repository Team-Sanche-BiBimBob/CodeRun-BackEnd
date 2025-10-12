package com.sanchae.coderun.domain.workbook.dto.request;

import com.sanchae.coderun.domain.practice.entity.PracticeType;

public record WorkbookUpdateRequestDto(
        Long languageId,
        PracticeType practiceType
) {}
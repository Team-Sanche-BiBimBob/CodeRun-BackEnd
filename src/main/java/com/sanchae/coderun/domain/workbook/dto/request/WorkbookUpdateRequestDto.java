package com.sanchae.coderun.domain.workbook.dto.request;

import com.sanchae.coderun.domain.practice.entity.PracticeType;
import jakarta.validation.constraints.Size;

public record WorkbookUpdateRequestDto(
        Long languageId,
        PracticeType practiceType,
        @Size(max = 255) String title,
        String description
) {}
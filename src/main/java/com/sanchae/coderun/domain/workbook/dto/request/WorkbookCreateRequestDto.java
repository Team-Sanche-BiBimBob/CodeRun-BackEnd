package com.sanchae.coderun.domain.workbook.dto.request;

import com.sanchae.coderun.domain.practice.entity.PracticeType;
import jakarta.validation.constraints.NotNull;

public record WorkbookCreateRequestDto(
        @NotNull Long languageId,
        @NotNull PracticeType practiceType
) {}
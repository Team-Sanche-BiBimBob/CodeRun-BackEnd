package com.sanchae.coderun.domain.workbook.dto.request;

import com.sanchae.coderun.domain.practice.entity.PracticeType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record WorkbookCreateRequestDto(
        @NotNull Long languageId,
        @NotNull PracticeType practiceType,
        @NotBlank @Size(max = 255) String title,
        String description
) {}
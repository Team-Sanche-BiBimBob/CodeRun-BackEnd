package com.sanchae.coderun.domain.workbook.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UpdateProblemRequestDto(
        @NotBlank String content
) {}
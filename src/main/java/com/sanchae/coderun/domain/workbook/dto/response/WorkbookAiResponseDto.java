package com.sanchae.coderun.domain.workbook.dto.response;

import com.sanchae.coderun.domain.practice.entity.PracticeType;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class WorkbookAiResponseDto {
    private String title;

    private String description;

    private List<String> problems;

    private Long workbookLanguageId;

    private PracticeType practiceType;

    private Long workbookProblemsCount;
}
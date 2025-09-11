package com.sanchae.coderun.domain.workbook.dto.request;

import com.sanchae.coderun.domain.practice.entity.PracticeType;
import com.sanchae.coderun.domain.workbook.entity.WorkbookProblemsCount;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WorkbookAiRequestDto {

    private Long workbookLanguageId;

    private PracticeType practiceType;

    private WorkbookProblemsCount workbookProblemsCount;

    private String customRequirements;
}

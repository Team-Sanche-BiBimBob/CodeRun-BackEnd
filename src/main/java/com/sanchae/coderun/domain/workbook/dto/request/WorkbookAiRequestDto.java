package com.sanchae.coderun.domain.workbook.dto.request;

import com.sanchae.coderun.domain.practice.entity.PracticeType;
import com.sanchae.coderun.domain.workbook.entity.WorkbookProblemsCount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkbookAiRequestDto {

    private Long workbookLanguageId;

    private PracticeType practiceType;

    private WorkbookProblemsCount workbookProblemsCount;

    private String customRequirements;
}

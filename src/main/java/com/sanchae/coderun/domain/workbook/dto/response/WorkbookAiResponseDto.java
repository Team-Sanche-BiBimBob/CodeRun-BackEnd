package com.sanchae.coderun.domain.workbook.dto.response;

import com.sanchae.coderun.domain.language.entity.Language;
import com.sanchae.coderun.domain.practice.entity.PracticeType;
import com.sanchae.coderun.domain.workbook.entity.WorkbookProblemsCount;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class WorkbookAiResponseDto {
    private List<String> problems;

    private Long workbookLanguageId;

    private PracticeType practiceType;

    private WorkbookProblemsCount workbookProblemsCount;
}
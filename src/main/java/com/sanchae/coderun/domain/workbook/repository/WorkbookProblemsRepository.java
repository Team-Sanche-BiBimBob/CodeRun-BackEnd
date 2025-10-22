package com.sanchae.coderun.domain.workbook.repository;

import com.sanchae.coderun.domain.workbook.entity.WorkbookProblems;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WorkbookProblemsRepository extends JpaRepository<WorkbookProblems, Long> {
    List<WorkbookProblems> findByWorkbookId(Long workbookId);
    int countByWorkbookId(Long workbookId);
}
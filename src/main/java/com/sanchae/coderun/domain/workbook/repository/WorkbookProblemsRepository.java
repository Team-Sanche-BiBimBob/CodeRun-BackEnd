package com.sanchae.coderun.domain.workbook.repository;

import com.sanchae.coderun.domain.workbook.entity.WorkbookProblems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkbookProblemsRepository extends JpaRepository<WorkbookProblems, Long> {
}

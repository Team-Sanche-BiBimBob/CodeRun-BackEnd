package com.sanchae.coderun.domain.workbook.repository;

import com.sanchae.coderun.domain.workbook.entity.Workbook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkbookRepository extends JpaRepository<Workbook, Long> {
}

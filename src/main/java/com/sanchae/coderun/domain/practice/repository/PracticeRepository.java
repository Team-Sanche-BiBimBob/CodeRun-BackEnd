package com.sanchae.coderun.domain.practice.repository;

import com.sanchae.coderun.domain.practice.entity.Practice;
import com.sanchae.coderun.domain.practice.entity.PracticeType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PracticeRepository extends JpaRepository<Practice, Long> {
    List<Practice> findAllByLanguage_idAndType(Long languageId, PracticeType typeId);
}
package com.sanchae.coderun.domain.language.repository;

import com.sanchae.coderun.domain.language.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Long> {
}
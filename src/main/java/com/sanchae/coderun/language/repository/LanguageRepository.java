package com.sanchae.coderun.language.repository;

import com.sanchae.coderun.language.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Long> {
}
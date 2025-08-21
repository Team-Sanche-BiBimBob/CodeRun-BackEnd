package com.sanchae.coderun.domain.practice.repository;

import com.sanchae.coderun.domain.practice.entity.PracticeSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PracticeSessionRepository extends JpaRepository<PracticeSession, Long> {
}
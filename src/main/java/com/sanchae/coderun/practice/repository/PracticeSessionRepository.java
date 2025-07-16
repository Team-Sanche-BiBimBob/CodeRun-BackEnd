package com.sanchae.coderun.practice.repository;

import com.sanchae.coderun.practice.entity.PracticeSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PracticeSessionRepository extends JpaRepository<PracticeSession, Long> {
}
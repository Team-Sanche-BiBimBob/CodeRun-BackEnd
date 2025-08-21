package com.sanchae.coderun.domain.practice.repository;

import com.sanchae.coderun.domain.practice.entity.Practice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PracticeRepository extends JpaRepository<Practice, Long> {
}
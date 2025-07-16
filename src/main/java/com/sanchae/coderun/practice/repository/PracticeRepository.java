package com.sanchae.coderun.practice.repository;

import com.sanchae.coderun.practice.entity.Practice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PracticeRepository extends JpaRepository<Practice, Long> {
}
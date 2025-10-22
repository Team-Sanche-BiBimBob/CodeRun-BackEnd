package com.sanchae.coderun.domain.problem.repository;

import com.sanchae.coderun.domain.problem.entity.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProblemRepository extends JpaRepository<Problem, Long> {
    List<Problem> findAllByPractice_id(Long practiceId);
}


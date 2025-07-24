package com.sanchae.coderun.domain.problem.repository;

import com.sanchae.coderun.domain.problem.entity.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemRepository extends JpaRepository<Problem, Long> {
}

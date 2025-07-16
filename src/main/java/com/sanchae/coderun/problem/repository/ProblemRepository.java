package com.sanchae.coderun.problem.repository;

import com.sanchae.coderun.problem.entity.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemRepository extends JpaRepository<Problem, Long> {
}

package com.sanchae.coderun.domain.classes.repository;

import com.sanchae.coderun.domain.classes.entity.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
}

package com.sanchae.coderun.classes.repository;

import com.sanchae.coderun.classes.entity.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
}
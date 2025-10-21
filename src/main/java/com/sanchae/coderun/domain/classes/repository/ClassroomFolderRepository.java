package com.sanchae.coderun.domain.classes.repository;

import com.sanchae.coderun.domain.classes.entity.ClassroomFolder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassroomFolderRepository extends JpaRepository<ClassroomFolder, Long> {
}

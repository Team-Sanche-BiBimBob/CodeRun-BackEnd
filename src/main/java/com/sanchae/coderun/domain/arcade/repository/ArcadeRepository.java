package com.sanchae.coderun.domain.arcade.repository;

import com.sanchae.coderun.domain.arcade.entity.ArcadeRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArcadeRepository extends JpaRepository<ArcadeRoom, Long> {
}

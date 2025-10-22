package com.sanchae.coderun.domain.arcade.repository;

import com.sanchae.coderun.domain.arcade.entity.ArcadeRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArcadeRepository extends JpaRepository<ArcadeRoom, Long> {
    Optional<Long> findArcadeRoomByPlayer1_Id(Long player1Id);
}

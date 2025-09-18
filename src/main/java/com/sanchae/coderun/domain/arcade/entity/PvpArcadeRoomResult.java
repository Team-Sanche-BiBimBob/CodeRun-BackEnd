package com.sanchae.coderun.domain.arcade.entity;

import jakarta.persistence.OneToOne;
import lombok.Builder;

@Builder
public record PvpArcadeRoomResult(
        @OneToOne
        ArcadeRoom arcadeRoom,
        Long player1Points,
        Long player2Points)
{ }

package com.sanchae.coderun.domain.arcade.entity;

import jakarta.persistence.OneToOne;
import lombok.Builder;

import java.time.Duration;

@Builder
public record TimeAttackArcadeRoomResult(
        @OneToOne
        ArcadeRoom arcadeRoom,
        Long player1Points,
        Long timeAttackResultTime)
{ }
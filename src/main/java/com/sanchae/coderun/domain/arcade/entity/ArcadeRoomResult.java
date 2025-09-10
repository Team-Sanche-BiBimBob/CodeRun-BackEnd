package com.sanchae.coderun.domain.arcade.entity;

import com.sanchae.coderun.domain.user.entity.User;
import jakarta.persistence.OneToOne;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ArcadeRoomResult(
        @OneToOne
        ArcadeRoom arcadeRoom,
        Long player1Points,
        Long player2Points,

        LocalDateTime finishTime,
        User winner)
{ }

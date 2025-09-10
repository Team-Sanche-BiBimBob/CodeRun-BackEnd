package com.sanchae.coderun.domain.arcade.dto.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class ArcadeRoomRequestDto {
    private Long player1Points;
    private Long player2Points;
}

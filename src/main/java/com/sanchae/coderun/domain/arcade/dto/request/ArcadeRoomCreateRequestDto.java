package com.sanchae.coderun.domain.arcade.dto.request;

import com.sanchae.coderun.domain.arcade.entity.ArcadeType;
import com.sanchae.coderun.domain.practice.entity.PracticeType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class ArcadeRoomCreateRequestDto {
    private ArcadeType arcadeType;
    private PracticeType eventType;

    private Long player1Id;
    private Long player2Id;
}
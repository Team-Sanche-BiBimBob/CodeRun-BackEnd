package com.sanchae.coderun.domain.arcade.dto.request;

import com.sanchae.coderun.domain.arcade.enums.ArcadeType;
import com.sanchae.coderun.domain.practice.entity.PracticeType;
import lombok.Data;

@Data
public class ArcadeRoomCreateRequestDto {
    private String title;
    private String description;

    private ArcadeType arcadeType;
    private PracticeType eventType;

    private Long player1Id;
    private Long player2Id;
}
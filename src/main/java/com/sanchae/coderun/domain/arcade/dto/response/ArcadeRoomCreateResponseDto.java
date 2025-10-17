package com.sanchae.coderun.domain.arcade.dto.response;

import com.sanchae.coderun.domain.arcade.enums.ArcadeType;
import com.sanchae.coderun.domain.practice.entity.PracticeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArcadeRoomCreateResponseDto {
    private Long roomId;
    private LocalDateTime startTime;
    private ArcadeType arcadeType;
    private PracticeType eventType;
}

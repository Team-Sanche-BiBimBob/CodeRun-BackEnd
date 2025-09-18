package com.sanchae.coderun.domain.arcade.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArcadeRoomPvpResultResponseDto {
    private Long winnerId;
    private LocalDateTime finishTime;
}

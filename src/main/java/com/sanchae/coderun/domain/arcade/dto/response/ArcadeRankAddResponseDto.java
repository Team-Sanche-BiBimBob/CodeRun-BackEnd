package com.sanchae.coderun.domain.arcade.dto.response;

import java.time.LocalDateTime;

public record ArcadeRankAddResponseDto(
        Long userId,
        LocalDateTime score,
        Long currentRank
) {
}

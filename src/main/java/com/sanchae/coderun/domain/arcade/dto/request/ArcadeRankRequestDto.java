package com.sanchae.coderun.domain.arcade.dto.request;

import com.sanchae.coderun.domain.arcade.enums.RankPeriod;

public record ArcadeRankRequestDto(
        Long arcadeRoomId,
        Long userId,
        Integer score,
        RankPeriod rankPeriod
) {
}

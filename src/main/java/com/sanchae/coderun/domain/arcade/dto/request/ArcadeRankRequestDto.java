package com.sanchae.coderun.domain.arcade.dto.request;

import com.sanchae.coderun.domain.arcade.enums.RankPeriod;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ArcadeRankRequestDto(
        Long userId,
        LocalDateTime score,
        RankPeriod rankPeriod
) {
}

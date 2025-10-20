package com.sanchae.coderun.domain.arcade.dto.response;

import com.sanchae.coderun.domain.arcade.dto.ArcadeRankDto;

import java.util.List;

public record ArcadeRankListResponseDto(
        List<ArcadeRankDto> rankings,
        int totalCount
) {}
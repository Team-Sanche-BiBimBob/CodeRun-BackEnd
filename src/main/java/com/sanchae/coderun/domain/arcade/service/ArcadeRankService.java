package com.sanchae.coderun.domain.arcade.service;

import com.sanchae.coderun.domain.arcade.dto.request.ArcadeRankRequestDto;
import com.sanchae.coderun.domain.arcade.dto.response.ArcadeRankAddResponseDto;
import com.sanchae.coderun.domain.arcade.dto.response.ArcadeRankListResponseDto;
import com.sanchae.coderun.domain.arcade.enums.RankPeriod;
import org.springframework.stereotype.Service;

@Service
public interface ArcadeRankService {
    public ArcadeRankAddResponseDto addRank(ArcadeRankRequestDto requestDto);
    public ArcadeRankListResponseDto getRankByPeriod(Long arcadeRoomId, RankPeriod rankPeriod);
}

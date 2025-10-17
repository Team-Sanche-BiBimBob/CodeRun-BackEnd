package com.sanchae.coderun.domain.arcade.service;

import com.sanchae.coderun.domain.arcade.dto.request.ArcadeRankRequestDto;
import com.sanchae.coderun.domain.arcade.dto.response.ArcadeRankAddResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface ArcadeRankService {
    public ArcadeRankAddResponseDto addRank(ArcadeRankRequestDto requestDto);
    public ArcadeRankAddResponseDto getRankByPeriod(ArcadeRankRequestDto requestDto);
}

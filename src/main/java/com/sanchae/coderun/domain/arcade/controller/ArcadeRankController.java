package com.sanchae.coderun.domain.arcade.controller;

import com.sanchae.coderun.domain.arcade.dto.request.ArcadeRankRequestDto;
import com.sanchae.coderun.domain.arcade.dto.response.ArcadeRankAddResponseDto;
import com.sanchae.coderun.domain.arcade.dto.response.ArcadeRankListResponseDto;
import com.sanchae.coderun.domain.arcade.enums.RankPeriod;
import com.sanchae.coderun.domain.arcade.service.ArcadeRankService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/arcade/rank")
public class ArcadeRankController {

    private final ArcadeRankService arcadeRankService;

    @PostMapping()
    public ArcadeRankAddResponseDto addRank(@RequestBody ArcadeRankRequestDto requestDto) {
        return arcadeRankService.addRank(requestDto);
    }

    @GetMapping("/{arcadeRoomId}")
    public ArcadeRankListResponseDto getRankByPeriod(@PathVariable Long arcadeRoomId, RankPeriod rankPeriod) {
        return arcadeRankService.getRankByPeriod(arcadeRoomId, rankPeriod);
    }
}

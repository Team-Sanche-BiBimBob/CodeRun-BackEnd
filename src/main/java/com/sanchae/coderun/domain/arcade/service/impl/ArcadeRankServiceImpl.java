package com.sanchae.coderun.domain.arcade.service.impl;

import com.sanchae.coderun.domain.arcade.dto.request.ArcadeRankRequestDto;
import com.sanchae.coderun.domain.arcade.dto.response.ArcadeRankAddResponseDto;
import com.sanchae.coderun.domain.arcade.enums.RankPeriod;
import com.sanchae.coderun.domain.arcade.repository.ArcadeRepository;
import com.sanchae.coderun.domain.arcade.service.ArcadeRankService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;

@RequiredArgsConstructor
public class ArcadeRankServiceImpl implements ArcadeRankService {

    private final ArcadeRepository arcadeRepository;
    private final RedisTemplate redisTemplate;

    private String getKey(RankPeriod rankPeriod) {
        if (rankPeriod == RankPeriod.DAILY) {
            return "ranking:daily:" +
                    LocalDate.now().format(DateTimeFormatter.ISO_DATE);
        } else if (rankPeriod == RankPeriod.MONTHLY) {
            LocalDate now = LocalDate.now();

            int weekOfYear = now.get(WeekFields.ISO.weekOfYear());

            return String.format("ranking:weekly:%d-W%02d",
                    now.getYear(), weekOfYear);
        } else if (rankPeriod == RankPeriod.WEEKLY) {
            LocalDate now = LocalDate.now();
            return String.format("ranking:monthly:%d-%02d",
                    now.getYear(), now.getMonthValue());
        }

        return null;
    }

    @Override
    public ArcadeRankAddResponseDto addRank(ArcadeRankRequestDto requestDto) {
        ZSetOperations<String, Long> zSet = redisTemplate.opsForZSet();

        String key = getKey(requestDto.rankPeriod());

        LocalDateTime now = requestDto.score();
        double score = now.toEpochSecond(ZoneOffset.UTC); // or toEpochMilli() / 1_000.0

        zSet.add(key, requestDto.userId(), score);
        redisTemplate.expire(key, Duration.ofDays(2));

        return null;
    }

    @Override
    public ArcadeRankAddResponseDto getRankByPeriod(ArcadeRankRequestDto requestDto) {
        return null;
    }
}

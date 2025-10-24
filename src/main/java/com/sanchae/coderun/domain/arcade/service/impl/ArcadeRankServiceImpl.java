
package com.sanchae.coderun.domain.arcade.service.impl;

import com.sanchae.coderun.domain.arcade.dto.ArcadeRankDto;
import com.sanchae.coderun.domain.arcade.dto.request.ArcadeRankRequestDto;
import com.sanchae.coderun.domain.arcade.dto.response.ArcadeRankAddResponseDto;
import com.sanchae.coderun.domain.arcade.dto.response.ArcadeRankListResponseDto;
import com.sanchae.coderun.domain.arcade.enums.RankPeriod;
import com.sanchae.coderun.domain.arcade.service.ArcadeRankService;
import com.sanchae.coderun.domain.user.entity.User;
import com.sanchae.coderun.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ArcadeRankServiceImpl implements ArcadeRankService {

    private final RedisTemplate<String, String> redisTemplate;
    private final UserRepository userRepository;

    private String getKey(RankPeriod rankPeriod) {
        if (rankPeriod == RankPeriod.DAILY) {
            return "ranking:daily:" +
                    LocalDate.now().format(DateTimeFormatter.ISO_DATE);
        } else if (rankPeriod == RankPeriod.WEEKLY) {
            LocalDate now = LocalDate.now();

            int weekOfYear = now.get(WeekFields.ISO.weekOfYear());

            return String.format("ranking:weekly:%d-W%02d",
                    now.getYear(), weekOfYear);
        } else if (rankPeriod == RankPeriod.MONTHLY) {
            LocalDate now = LocalDate.now();
            return String.format("ranking:monthly:%d-%02d",
                    now.getYear(), now.getMonthValue());
        }

        return null;
    }

    @Override
    public ArcadeRankAddResponseDto addRank(ArcadeRankRequestDto requestDto) {
        ZSetOperations<String, String> zSet = redisTemplate.opsForZSet();

        String key = getKey(requestDto.rankPeriod());

        if (key == null) {
            throw new IllegalArgumentException("Invalid rank period");
        }

        double score = requestDto.score();

        zSet.add(key, String.valueOf(requestDto.userId()), score);
        redisTemplate.expire(key, Duration.ofDays(2));

        User user = userRepository.findById(requestDto.userId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + requestDto.userId()));

        return new ArcadeRankAddResponseDto(
                user.getUsername(),
                requestDto.score()
        );
    }

    @Override
    public ArcadeRankListResponseDto getRankByPeriod(RankPeriod rankPeriod) {

        String key = getKey(rankPeriod);

        if (key == null) {
            throw new IllegalArgumentException("Invalid rank period");
        }

        ZSetOperations<String, String> zSet = redisTemplate.opsForZSet();

        Set<ZSetOperations.TypedTuple<String>> rankingsWithScores =
                zSet.rangeWithScores(key, 0, -1);

        if (rankingsWithScores == null || rankingsWithScores.isEmpty()) {
            return new ArcadeRankListResponseDto(List.of(), 0);
        }

        List<ArcadeRankDto> rankings = new ArrayList<>();
        int rank = 1;

        for (ZSetOperations.TypedTuple<String> tuple : rankingsWithScores) {
            String userIdStr = tuple.getValue();
            Double scoreValue = tuple.getScore();

            if (userIdStr != null && scoreValue != null) {
                try {
                    Long userId = Long.parseLong(userIdStr);

                    final int currentRank = rank;
                    final Integer completionTimeSeconds = scoreValue.intValue();

                    userRepository.findById(userId).ifPresent(user -> {
                        rankings.add(new ArcadeRankDto(
                                currentRank,
                                user.getUsername(),
                                completionTimeSeconds
                        ));
                    });

                    if (userRepository.findById(userId).isPresent()) {
                        rank++;
                    }
                } catch (NumberFormatException e) {
                    continue;
                }
            }
        }

        return new ArcadeRankListResponseDto(rankings, rankings.size());
    }
}
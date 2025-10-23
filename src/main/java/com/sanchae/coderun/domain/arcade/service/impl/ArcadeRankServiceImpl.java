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

    /**
     * ArcadeRoomId별 랭킹 키 생성
     * 예: ranking:room:123:daily:2025-10-23
     */
    private String getKey(Long arcadeRoomId, RankPeriod rankPeriod) {
        String baseKey = "ranking:room:" + arcadeRoomId;

        if (rankPeriod == RankPeriod.DAILY) {
            return baseKey + ":daily:" +
                    LocalDate.now().format(DateTimeFormatter.ISO_DATE);
        } else if (rankPeriod == RankPeriod.WEEKLY) {
            LocalDate now = LocalDate.now();
            int weekOfYear = now.get(WeekFields.ISO.weekOfYear());
            return String.format("%s:weekly:%d-W%02d",
                    baseKey, now.getYear(), weekOfYear);
        } else if (rankPeriod == RankPeriod.MONTHLY) {
            LocalDate now = LocalDate.now();
            return String.format("%s:monthly:%d-%02d",
                    baseKey, now.getYear(), now.getMonthValue());
        }

        return null;
    }

    @Override
    public ArcadeRankAddResponseDto addRank(ArcadeRankRequestDto requestDto) {
        ZSetOperations<String, String> zSet = redisTemplate.opsForZSet();

        String key = getKey(requestDto.arcadeRoomId(), requestDto.rankPeriod());

        if (key == null) {
            throw new IllegalArgumentException("Invalid rank period");
        }

        double score = requestDto.score();

        // Redis ZSet에 추가 (낮은 점수가 더 높은 순위)
        zSet.add(key, String.valueOf(requestDto.userId()), score);

        // 만료 시간 설정 (기간별로 다르게 설정 가능)
        Duration expiration = getExpiration(requestDto.rankPeriod());
        redisTemplate.expire(key, expiration);

        User user = userRepository.findById(requestDto.userId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + requestDto.userId()));

        return new ArcadeRankAddResponseDto(
                user.getUsername(),
                requestDto.score()
        );
    }

    /**
     * 기간별 만료 시간 설정
     */
    private Duration getExpiration(RankPeriod rankPeriod) {
        return switch (rankPeriod) {
            case DAILY -> Duration.ofDays(2);
            case WEEKLY -> Duration.ofDays(8);
            case MONTHLY -> Duration.ofDays(32);
        };
    }

    @Override
    public ArcadeRankListResponseDto getRankByPeriod(Long arcadeRoomId, RankPeriod rankPeriod) {

        String key = getKey(arcadeRoomId, rankPeriod);

        if (key == null) {
            throw new IllegalArgumentException("Invalid rank period");
        }

        ZSetOperations<String, String> zSet = redisTemplate.opsForZSet();

        // 점수 오름차순으로 정렬 (낮은 점수가 1등)
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
                    return null;
                }
            }
        }

        return new ArcadeRankListResponseDto(rankings, rankings.size());
    }
}
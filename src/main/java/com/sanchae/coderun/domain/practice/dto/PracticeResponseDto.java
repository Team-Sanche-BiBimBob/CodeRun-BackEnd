package com.sanchae.coderun.domain.practice.dto;

import com.sanchae.coderun.domain.practice.entity.Practice;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
public class PracticeResponseDto {
    private Long id;
    private String title;
    private String description;
    private String level;
    private Long languageId;

    public PracticeResponseDto(Long id, String title, String description, String level, Long languageId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.level = level;
        this.languageId = languageId;
    }

    public PracticeResponseDto(Practice practice) {
        this.id = practice.getId();
        this.title = practice.getTitle();
        this.description = practice.getDescription();
        this.level = practice.getLevel();
        this.languageId = practice.getLanguage().getId();
    }

    public static PracticeResponseDto fromEntity(Practice practice) {
        return PracticeResponseDto.builder()
                .id(practice.getId())
                .title(practice.getTitle())
                .description(practice.getDescription())
                .level(practice.getLevel())
                .languageId(practice.getLanguage().getId())
                .build();
    }
}

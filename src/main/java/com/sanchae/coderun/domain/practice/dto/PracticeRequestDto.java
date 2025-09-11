package com.sanchae.coderun.domain.practice.dto;

import com.sanchae.coderun.domain.practice.entity.PracticeType;
import lombok.Data;

@Data
public class PracticeRequestDto {
    private String title;
    private String description;
    private String level;
    private Long languageId; // <- 수정됨
    private PracticeType type;
}
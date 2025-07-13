package com.sanchae.coderun.practice.dto;

import com.sanchae.coderun.practice.entity.PracticeType;
import lombok.Data;

@Data
public class PracticeRequestDto {
    private String title;
    private String description;
    private Long level;
    private PracticeType type;
    private Long languageId; // <- 수정됨
}
package com.sanchae.coderun.domain.practice.dto;

import lombok.Data;

@Data
public class PracticeRequestDto {
    private String title;
    private String description;
    private String level;
    private Long languageId; // <- 수정됨
}
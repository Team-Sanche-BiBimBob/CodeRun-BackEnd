package com.sanchae.coderun.practice.dto;

import lombok.Data;

@Data
public class PracticeResponseDto {
    private Long id;
    private String title;
    private String description;
    private String level;
    private String language;

    public PracticeResponseDto() {}

    public PracticeResponseDto(Long id, String title, String description, String level, String language) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.level = level;
        this.language = language;
    }
}

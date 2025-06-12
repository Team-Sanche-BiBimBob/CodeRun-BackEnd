package com.sanchae.coderun.practice.dto;

import lombok.Data;

@Data
public class PracticeRequestDto {
    private String title;
    private String description;
    private String level;
    private String language;

    public PracticeRequestDto() {}

    public PracticeRequestDto(String title, String description, String level, String language) {
        this.title = title;
        this.description = description;
        this.level = level;
        this.language = language;
    }
}
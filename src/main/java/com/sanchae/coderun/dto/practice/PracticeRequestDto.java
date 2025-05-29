package com.sanchae.coderun.dto.practice;

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

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLevel() {
        return level;
    }

    public String getLanguage() {
        return language;
    }
}
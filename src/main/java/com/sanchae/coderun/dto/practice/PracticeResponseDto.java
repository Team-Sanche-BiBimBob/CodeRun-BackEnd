package com.sanchae.coderun.dto.practice;

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

    public Long getId() {
        return id;
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

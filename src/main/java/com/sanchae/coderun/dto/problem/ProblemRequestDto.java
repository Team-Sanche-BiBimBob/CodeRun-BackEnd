package com.sanchae.coderun.dto.problem;

public class ProblemRequestDto {
    private String title;
    private String description;
    private String level;
    private String language;

    public ProblemRequestDto() {}
    public ProblemRequestDto(String title, String description, String level, String language) {
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

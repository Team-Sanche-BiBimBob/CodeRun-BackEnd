package com.sanchae.coderun.dto.language;

public class LanguageResponseDto {
    private Long id;
    private String name;
    private String version;

    public LanguageResponseDto() {}

    public LanguageResponseDto(Long id, String name, String version) {
        this.id = id;
        this.name = name;
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }
}
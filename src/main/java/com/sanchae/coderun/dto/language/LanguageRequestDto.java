package com.sanchae.coderun.dto.language;

public class LanguageRequestDto {
    private String name;
    private String version;

    public LanguageRequestDto() {}

    public LanguageRequestDto(String name, String version) {
        this.name = name;
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }
}
package com.sanchae.coderun.dto.userlanguage;

public class UserLanguageResponseDto {
    private Long id;
    private Long userId;
    private Long languageId;

    public UserLanguageResponseDto() {}

    public UserLanguageResponseDto(Long id, Long userId, Long languageId) {
        this.id = id;
        this.userId = userId;
        this.languageId = languageId;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getLanguageId() {
        return languageId;
    }
}
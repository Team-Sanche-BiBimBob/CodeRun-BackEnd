package com.sanchae.coderun.dto.userlanguage;


public class UserLanguageRequestDto {
    private Long userId;
    private Long languageId;

    public UserLanguageRequestDto() {}

    public UserLanguageRequestDto(Long userId, Long languageId) {
        this.userId = userId;
        this.languageId = languageId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getLanguageId() {
        return languageId;
    }
}
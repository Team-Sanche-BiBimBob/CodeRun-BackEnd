package com.sanchae.coderun.domain.user.dto.userlanguage;

import lombok.Data;

@Data
public class UserLanguageRequestDto {
    private Long userId;
    private Long languageId;

    public UserLanguageRequestDto() {}

    public UserLanguageRequestDto(Long userId, Long languageId) {
        this.userId = userId;
        this.languageId = languageId;
    }
}
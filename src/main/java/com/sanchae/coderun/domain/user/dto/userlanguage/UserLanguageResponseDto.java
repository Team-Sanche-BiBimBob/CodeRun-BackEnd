package com.sanchae.coderun.domain.user.dto.userlanguage;

import lombok.Data;

@Data
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
}
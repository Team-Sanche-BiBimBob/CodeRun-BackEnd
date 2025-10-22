package com.sanchae.coderun.domain.user.dto.profile.response;

import com.sanchae.coderun.domain.language.entity.Language;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileResponseDto {

    private Long id;
    private Long userId;

    private String userDescription;

    private Language recentlyStudiedLanguage;
    private Long recentlyStudiedLanguageProgress;
    private Long recentlyStudiedLanguageScore;

    private Language mostStudiedLanguage;
    private Long mostStudiedLanguageProgress;
    private Long mostStudiedLanguageScore;
}
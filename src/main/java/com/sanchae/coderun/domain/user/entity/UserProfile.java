package com.sanchae.coderun.domain.user.entity;

import com.sanchae.coderun.domain.language.entity.Language;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Long userId;

    private String profileImage;

    private String userDescription;

    @OneToOne
    private Language recentlyStudiedLanguage;
    private Long recentlyStudiedLanguageProgress;
    private Long recentlyStudiedLanguageScore;

    @OneToOne
    private Language mostStudiedLanguage;
    private Long mostStudiedLanguageProgress;
    private Long mostStudiedLanguageScore;
}
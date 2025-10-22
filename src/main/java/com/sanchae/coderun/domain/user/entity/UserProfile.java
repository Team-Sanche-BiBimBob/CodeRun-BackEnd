package com.sanchae.coderun.domain.user.entity;

import com.sanchae.coderun.domain.language.entity.Language;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 기본 키

    @Column(unique = true)
    private Long userId;

    private String profileImage; // 임시

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

package com.sanchae.coderun.domain.user.service.impl;

import com.sanchae.coderun.domain.language.entity.Language;
import com.sanchae.coderun.domain.language.repository.LanguageRepository;
import com.sanchae.coderun.domain.user.dto.profile.request.RenewalUserMostStudiedLanguageRequestDto;
import com.sanchae.coderun.domain.user.dto.profile.request.RenewalUserRecentlyStudiedLanguageRequestDto;
import com.sanchae.coderun.domain.user.dto.profile.request.UpdateUserProfileRequestDto;
import com.sanchae.coderun.domain.user.dto.profile.response.RenewalUserMostStudiedLanguageResponseDto;
import com.sanchae.coderun.domain.user.dto.profile.response.RenewalUserRecentlyStudiedLanguageResponseDto;
import com.sanchae.coderun.domain.user.dto.profile.response.UpdateUserProfileResponseDto;
import com.sanchae.coderun.domain.user.dto.profile.response.UserProfileResponseDto;
import com.sanchae.coderun.domain.user.entity.User;
import com.sanchae.coderun.domain.user.entity.UserProfile;
import com.sanchae.coderun.domain.user.repository.UserProfileRepository;
import com.sanchae.coderun.domain.user.repository.UserRepository;
import com.sanchae.coderun.domain.user.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final LanguageRepository languageRepository;
    private final UserRepository userRepository;

    @Override
    public RenewalUserMostStudiedLanguageResponseDto renewalUserMostStudiedLanguage(Long id, RenewalUserMostStudiedLanguageRequestDto requestDto) {
        UserProfile userProfile = userProfileRepository.findById(id).orElse(null);

        if (userProfile == null) {
            return null;
        }

        Language language = languageRepository.findById(requestDto.getMostStudiedLanguageId()).orElse(null);


        UserProfile newUserProfile = UserProfile.builder()
                .id(id)
                .userId(userProfile.getUserId())
                .profileImage(userProfile.getProfileImage())
                .userDescription(userProfile.getUserDescription())
                .recentlyStudiedLanguage(userProfile.getRecentlyStudiedLanguage())
                .recentlyStudiedLanguageProgress(userProfile.getRecentlyStudiedLanguageProgress())
                .recentlyStudiedLanguageScore(userProfile.getRecentlyStudiedLanguageScore())
                .mostStudiedLanguage(language)
                .mostStudiedLanguageProgress(requestDto.getMostStudiedLanguageProgress())
                .mostStudiedLanguageScore(requestDto.getMostStudiedLanguageScore())
                .build();

        UserProfile savedUserProfile = userProfileRepository.save(newUserProfile);

        return RenewalUserMostStudiedLanguageResponseDto.builder()
                .mostStudiedLanguage(savedUserProfile.getMostStudiedLanguage())
                .mostStudiedLanguageProgress(savedUserProfile.getMostStudiedLanguageProgress())
                .mostStudiedLanguageScore(savedUserProfile.getMostStudiedLanguageScore())
                .build();
    }

    @Override
    public RenewalUserRecentlyStudiedLanguageResponseDto renewalUserRecentlyStudiedLanguage(Long id, RenewalUserRecentlyStudiedLanguageRequestDto requestDto) {
        UserProfile userProfile = userProfileRepository.findById(id).orElse(null);

        if (userProfile == null) {
            return null;
        }

        Language language = languageRepository.findById(requestDto.getRecentlyStudiedLanguageId()).orElse(null);

        UserProfile newUserProfile = UserProfile.builder()
                .id(id)
                .userId(userProfile.getUserId())
                .profileImage(userProfile.getProfileImage())
                .userDescription(userProfile.getUserDescription())
                .recentlyStudiedLanguage(language)
                .recentlyStudiedLanguageProgress(requestDto.getRecentlyStudiedLanguageProgress())
                .recentlyStudiedLanguageScore(requestDto.getRecentlyStudiedLanguageScore())
                .mostStudiedLanguage(userProfile.getMostStudiedLanguage())
                .mostStudiedLanguageProgress(userProfile.getMostStudiedLanguageProgress())
                .mostStudiedLanguageScore(userProfile.getMostStudiedLanguageScore())
                .build();

        UserProfile savedUserProfile = userProfileRepository.save(newUserProfile);

        return RenewalUserRecentlyStudiedLanguageResponseDto.builder()
                .recentlyStudiedLanguage(savedUserProfile.getRecentlyStudiedLanguage())
                .recentlyStudiedLanguageScore(savedUserProfile.getRecentlyStudiedLanguageScore())
                .recentlyStudiedLanguageProgress(savedUserProfile.getRecentlyStudiedLanguageProgress())
                .build();
    }

    @Override
    public UserProfileResponseDto getUserProfile(Long id) {
        UserProfile userProfile = userProfileRepository.findById(id).orElseThrow(()
                -> new RuntimeException("찾으시려는 사용자의 id가 없습니다."));

        User user = userRepository.findById(userProfile.getUserId()).orElse(null);

        return UserProfileResponseDto.builder()
                .id(userProfile.getId())
                .userId(userProfile.getUserId())
                .username(user.getUsername())
                .profileImage(userProfile.getProfileImage())
                .userDescription(userProfile.getUserDescription())
                .recentlyStudiedLanguage(userProfile.getRecentlyStudiedLanguage())
                .recentlyStudiedLanguageProgress(userProfile.getRecentlyStudiedLanguageProgress())
                .recentlyStudiedLanguageScore(userProfile.getRecentlyStudiedLanguageScore())
                .mostStudiedLanguage(userProfile.getMostStudiedLanguage())
                .mostStudiedLanguageProgress(userProfile.getMostStudiedLanguageProgress())
                .mostStudiedLanguageScore(userProfile.getMostStudiedLanguageScore())
                .build();
    }

    @Override
    public UpdateUserProfileResponseDto updateUserProfile(Long userId, Long id, UpdateUserProfileRequestDto updateUserProfileRequestDto) {

        UserProfile userProfile = userProfileRepository.findById(id).orElse(null);

        if (userProfile == null) {
            return null;
        }

        UserProfile newUserProfile = UserProfile.builder()
                .id(id)
                .userId(userId)
                .profileImage(updateUserProfileRequestDto.getProfileImage())
                .userDescription(updateUserProfileRequestDto.getUserDescription())
                .recentlyStudiedLanguage(userProfile.getRecentlyStudiedLanguage())
                .recentlyStudiedLanguageProgress(userProfile.getRecentlyStudiedLanguageProgress())
                .recentlyStudiedLanguageScore(userProfile.getRecentlyStudiedLanguageScore())
                .mostStudiedLanguage(userProfile.getMostStudiedLanguage())
                .mostStudiedLanguageProgress(userProfile.getMostStudiedLanguageProgress())
                .mostStudiedLanguageScore(userProfile.getMostStudiedLanguageScore())
                .build();

        UserProfile savedUserProfile = userProfileRepository.save(newUserProfile);

        return UpdateUserProfileResponseDto.builder()
                .userId(savedUserProfile.getUserId())
                .userDescription(savedUserProfile.getUserDescription())
                .build();
    }
}

package com.sanchae.coderun.domain.practice.service.impl;

import com.sanchae.coderun.domain.language.entity.Language;
import com.sanchae.coderun.domain.language.repository.LanguageRepository;
import com.sanchae.coderun.domain.practice.dto.PracticeRequestDto;
import com.sanchae.coderun.domain.practice.dto.PracticeResponseDto;
import com.sanchae.coderun.domain.practice.entity.Practice;
import com.sanchae.coderun.domain.practice.entity.PracticeType;
import com.sanchae.coderun.domain.practice.repository.PracticeRepository;
import com.sanchae.coderun.domain.practice.service.PracticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PracticeServiceImpl implements PracticeService {

    private final PracticeRepository practiceRepository;
    private final LanguageRepository languageRepository;

    @Override
    public PracticeResponseDto createPractice(PracticeRequestDto requestDto) {

        Language language = languageRepository.findById(requestDto.getLanguageId()).orElse(null);

        Practice practice = Practice.builder()
                .title(requestDto.getTitle())
                .level(requestDto.getLevel())
                .description(requestDto.getDescription())
                .language(language)
                .build();

        practiceRepository.save(practice);

        return PracticeResponseDto.builder()
                .id(practice.getId())
                .title(practice.getTitle())
                .description(practice.getDescription())
                .level(practice.getLevel())
                .languageId(requestDto.getLanguageId())
                .build();
    }

    @Override
    public List<PracticeResponseDto> getAllPractice() {
        List<Practice> practices = practiceRepository.findAll();
        return practices.stream().map(p-> PracticeResponseDto.builder()
                .title(p.getTitle())
                .level(p.getLevel())
                .description(p.getDescription())
                .languageId(p.getLanguage().getId())
                .id(p.getId())
                .build())
                .toList();
    }

    @Override
    public List<PracticeResponseDto> getAllPracticeByLanguageIdAndType(Long languageId, PracticeType type) {
        List<Practice> practices = practiceRepository.findAllByLanguage_idAndType(languageId, type);
        return practices.stream().map(p -> PracticeResponseDto.builder()
                        .title(p.getTitle())
                        .level(p.getLevel())
                        .description(p.getDescription())
                        .languageId(p.getLanguage().getId())
                        .id(p.getId())
                        .build())
                .toList();

    }


    @Override
    public PracticeResponseDto getPracticeById(Long id) {
        Practice p = practiceRepository.findById(id).orElse(null);
        if(p != null){
            return new PracticeResponseDto(p);
        }
        return null;
    }

//    @Override
//    public List<ProblemResponseDto> getPracticeProblems(Long practiceId) {
//        return problemService.findAllProblemsByPracticeId(practiceId);
//    }
}

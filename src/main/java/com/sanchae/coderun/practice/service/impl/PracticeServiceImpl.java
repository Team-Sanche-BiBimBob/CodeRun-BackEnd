package com.sanchae.coderun.practice.service.impl;

import com.sanchae.coderun.language.entity.Language;
import com.sanchae.coderun.language.repository.LanguageRepository;
import com.sanchae.coderun.practice.dto.PracticeRequestDto;
import com.sanchae.coderun.practice.dto.PracticeResponseDto;
import com.sanchae.coderun.practice.entity.Practice;
import com.sanchae.coderun.practice.repository.PracticeRepository;
import com.sanchae.coderun.practice.service.PracticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PracticeServiceImpl implements PracticeService {

    private final PracticeRepository practiceRepository;
    private final LanguageRepository languageRepository;

    public List<Practice> getAllPractice() {
        return practiceRepository.findAll();
    }

    public Practice getPracticeById(Long id) {
        return practiceRepository.findById(id).orElse(null);
    }

    public Practice searchPractice(PracticeRequestDto practiceRequestDto) {
        return new Practice(); // TODO
    }


    public PracticeResponseDto createPractice(PracticeRequestDto practiceRequestDto) {

        Language language = languageRepository.findById(practiceRequestDto.getLanguageId()).orElse(null);

        Practice practice = Practice.builder()
                .title(practiceRequestDto.getTitle())
                .description(practiceRequestDto.getDescription())
                .type(practiceRequestDto.getType())
                .level(3L)
                .language(language)
                .build();

        practiceRepository.save(practice);

        return PracticeResponseDto.builder()
                .id(practice.getId())
                .title(practice.getTitle())
                .languageId(practice.getLanguage().getId())
                .build();
    }


}

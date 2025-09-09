package com.sanchae.coderun.domain.practice.controller;

import com.sanchae.coderun.domain.practice.entity.PracticeType;
import org.springframework.core.convert.converter.Converter;

public class PracticeTypeConverter implements Converter<String, PracticeType> {
    @Override
    public PracticeType convert(String source) {
        return PracticeType.valueOf(source);
    }
}

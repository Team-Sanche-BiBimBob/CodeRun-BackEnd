package com.sanchae.coderun.domain.workbook.service;

import com.sanchae.coderun.domain.language.entity.Language;
import com.sanchae.coderun.domain.language.repository.LanguageRepository;
import com.sanchae.coderun.domain.practice.entity.PracticeType;
import com.sanchae.coderun.domain.workbook.dto.request.WorkbookAiRequestDto;
import com.sanchae.coderun.domain.workbook.dto.response.WorkbookAiResponseDto;
import com.sanchae.coderun.domain.workbook.entity.Workbook;
import com.sanchae.coderun.domain.workbook.entity.WorkbookProblems;
import com.sanchae.coderun.domain.workbook.repository.WorkbookProblemsRepository;
import com.sanchae.coderun.domain.workbook.repository.WorkbookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkbookService {

    private final WorkbookRepository workbookRepository;
    private final WorkbookProblemsRepository workbookProblemsRepository;
    private final LanguageRepository languageRepository;
    private final OpenAiChatModel openAiChatModel;

    @Transactional
    public WorkbookAiResponseDto generateWorkbook(WorkbookAiRequestDto requestDto) {

        Language language = languageRepository.findById(requestDto.getWorkbookLanguageId())
                .orElseThrow(() -> new RuntimeException(""));

        String languageName = language.getName();

        OpenAiChatOptions openAiChatOptions = OpenAiChatOptions.builder()
                .model("gpt-4.1-mini")
                .temperature(1.0)
                .build();

        AssistantMessage assistantMessage = new AssistantMessage("");

        SystemMessage systemMessage = new SystemMessage(
                "문제집에서 제공되는 텍스트의 무조건 맨 앞과 무조건 맨 마지막엔 &&&&&을 기본적으로 누르고, 그 외엔 모든 문제를 대시 5번을 누르고 생성하라. (맨 앞 줄에는 대시 넣지 않기)" +
                        "만약 단어를 생성할 경우엔, 딱 내라는 갯수만큼의 단어만 내고, 한 단어가 나오면 바로 엔터 후 대시 5번을 누르고 생성하라. 그리고, 코드 문장이나 풀코드는 절대로 내지마라." +
                        "들여쓰기를 제발 좀 제대로 구현해라" +
                        "또한, 문제 내용만 내고 문제 1 같은 제목이나 문제 내용을 포함한 어떠한 사족도 달지 말 것. " +
                        "형식은 무조건 텍스트 형식으로 하고, 마크다운은 사용하지 말기 " +
                        "추가로, ~~를 맞춰라라는 문제는 내지 말고, 코드만 적어서 내자 " +
                        "추가로, 주석은 넣지말자"
        );

        String practicePrompt;

        if (requestDto.getPracticeType().equals(PracticeType.PRACTICE_WORD)) {
            practicePrompt = "무작위 예약어 또는 자주 쓰이는 한 글자가 아닌 변수명 또는 클래스명을 한 단어로 딱";
        } else if (requestDto.getPracticeType().equals(PracticeType.PRACTICE_SENTENCE)) {
            practicePrompt = "무작위 문법의 한 줄짜리 코드 ";
        } else {
            practicePrompt = "무작위 각각 최소한 무조건 10~12줄의 스택, 큐 등의 중~고등 알고리즘 코드 ";
        }
        UserMessage userMessage = new UserMessage(
                languageName + "언어를 사용해서" +
                        requestDto.getCustomRequirements() + "에 관련한 분야 관련된 " +
                        practicePrompt + "무조건 딱" + requestDto.getWorkbookProblemsCount() + "개만 내라."
        );

        Prompt prompt = new Prompt(List.of(systemMessage, userMessage, assistantMessage), openAiChatOptions);

        ChatResponse response = openAiChatModel.call(prompt);

        String aiResponse = response.getResults().getFirst().getOutput().getText();
        if (aiResponse == null) {
            throw new RuntimeException("AI response is null.");
        }
        String[] parts = aiResponse.split("&&&&&");
        if (parts.length < 2) {
            throw new RuntimeException("AI response did not contain the expected separator.");
        }

        // 문제들을 분리하고 정리
        List<String> cleanedProblems = Arrays.stream(parts[1].trim().split("-----"))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());

        // 문제 엔티티 생성 및 저장
        List<WorkbookProblems> workbookProblemsArray = cleanedProblems.stream()
                .map(problem -> WorkbookProblems.builder().content(problem).build())
                .collect(Collectors.toList());

        List<WorkbookProblems> savedProblems = workbookProblemsRepository.saveAll(workbookProblemsArray);

        // 문제집 생성 및 저장
        Workbook workbook = Workbook.builder()
                .title(requestDto.getTitle())
                .description(requestDto.getDescription())
                .workbookLanguage(languageRepository.findById(requestDto.getWorkbookLanguageId()).orElse(null))
                .workbookProblemsCount(requestDto.getWorkbookProblemsCount())
                .practiceType(requestDto.getPracticeType())
                .workbookProblems(savedProblems)
                .build();

        workbookRepository.save(workbook);

        // 응답 생성
        return WorkbookAiResponseDto.builder()
                .title(requestDto.getTitle())
                .description(requestDto.getDescription())
                .problems(cleanedProblems)
                .workbookProblemsCount(requestDto.getWorkbookProblemsCount())
                .workbookLanguageId(requestDto.getWorkbookLanguageId())
                .practiceType(requestDto.getPracticeType())
                .build();
    }

    public List<Workbook> getAllWorkbooks() {
        return workbookRepository.findAll();
    }

    public Workbook getWorkbookById(Long id) {
        return workbookRepository.findById(id).orElse(null);
    }

    public void deleteWorkbookById(Long id) {
        workbookRepository.deleteById(id);
    }

    public List<WorkbookProblems> getAllProblemInWorkbook(Long workbookId) {
        Workbook workbook = workbookRepository.findById(workbookId).orElse(null);

        if (workbook == null) {
            return null;
        }

        return workbook.getWorkbookProblems();
    }

    public WorkbookProblems getWorkbookProblems(Long workbookId) {
        return workbookProblemsRepository.findById(workbookId)
                .orElseThrow(() -> new RuntimeException("문제를 찾을 수 없습니다."));
    }
}

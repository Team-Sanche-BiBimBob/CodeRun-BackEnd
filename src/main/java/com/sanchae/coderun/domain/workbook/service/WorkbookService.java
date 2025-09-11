package com.sanchae.coderun.domain.workbook.service;

import com.sanchae.coderun.domain.language.entity.Language;
import com.sanchae.coderun.domain.language.repository.LanguageRepository;
import com.sanchae.coderun.domain.practice.entity.PracticeType;
import com.sanchae.coderun.domain.workbook.dto.request.WorkbookAiRequestDto;
import com.sanchae.coderun.domain.workbook.dto.response.WorkbookAiResponseDto;
import com.sanchae.coderun.domain.workbook.entity.Workbook;
import com.sanchae.coderun.domain.workbook.entity.WorkbookProblems;
import com.sanchae.coderun.domain.workbook.entity.WorkbookProblemsCount;
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

import java.util.ArrayList;
import java.util.List;

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
        int problemsCount;

        if (requestDto.getPracticeType().equals(PracticeType.PRACTICE_WORD)) {
            practicePrompt = "무작위 예약어 또는 자주 쓰이는 한 글자가 아닌 변수명 또는 클래스명을 한 단어로 딱";
        } else if (requestDto.getPracticeType().equals(PracticeType.PRACTICE_SENTENCE)) {
            practicePrompt = "무작위 문법의 한 줄짜리 코드 ";
        } else {
            practicePrompt = "무작위 반드시 각각 10~12줄의 연결된 프로그램 ";
        }

        if (requestDto.getWorkbookProblemsCount().equals(WorkbookProblemsCount.WPC_5)) {
            problemsCount = 5;
        } else if (requestDto.getWorkbookProblemsCount().equals(WorkbookProblemsCount.WPC_10)) {
            problemsCount = 10;
        } else {
            problemsCount = 15;
        }

        UserMessage userMessage = new UserMessage(
                languageName + "언어를 사용해서" +
                        requestDto.getCustomRequirements() + "에 관련한 분야 관련된 " +
                        practicePrompt + "무조건 딱" + problemsCount + "개만 내라."
        );

        Prompt prompt = new Prompt(List.of(systemMessage, userMessage, assistantMessage), openAiChatOptions);

        ChatResponse response = openAiChatModel.call(prompt);

        String unelaboratedStringAIResponse = response.toString();
        String stringAIResponse[] = unelaboratedStringAIResponse.split("&&&&&");
        String[] workbookProblems = stringAIResponse[1].split("-----");

        WorkbookProblems splitedWorkbookProblems;
        List<WorkbookProblems> workbookProblemsArray = new ArrayList<>();

        for (String workbookProblem : workbookProblems) {
            splitedWorkbookProblems = WorkbookProblems.builder()
                    .content(workbookProblem)
                    .build();

            workbookProblemsArray.add(splitedWorkbookProblems);
        }

        List<WorkbookProblems> firstSavedWorkbookProblems = workbookProblemsRepository.saveAll(workbookProblemsArray);

        Workbook workbook = Workbook.builder()
                .workbookLanguage(languageRepository.findById(requestDto.getWorkbookLanguageId()).orElse(null))
                .workbookProblemsCount(requestDto.getWorkbookProblemsCount())
                .practiceType(requestDto.getPracticeType())
                .workbookProblems(firstSavedWorkbookProblems)
                .build();

        Workbook savedWorkbook = workbookRepository.save(workbook);

        for (WorkbookProblems savingWorkbookProblems : firstSavedWorkbookProblems) {
            savingWorkbookProblems.setWorkbookId(savedWorkbook.getId());
            workbookProblemsRepository.save(savingWorkbookProblems);
        }

        return WorkbookAiResponseDto.builder()
                .message(response.getResult().getOutput().getText())
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

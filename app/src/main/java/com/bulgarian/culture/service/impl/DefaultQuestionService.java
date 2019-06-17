package com.bulgarian.culture.service.impl;

import com.bulgarian.culture.database.DatabaseHelper;
import com.bulgarian.culture.model.dto.QuestionViewModel;
import com.bulgarian.culture.model.enity.Question;
import com.bulgarian.culture.repository.api.QuestionRepository;
import com.bulgarian.culture.repository.impl.DefaultQuestionRepository;
import com.bulgarian.culture.service.api.QuestionService;
import com.bulgarian.culture.util.RandomGenerator;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.bulgarian.culture.constants.Constants.QUESTIONS_BUFFER_LENGTH;
import static com.bulgarian.culture.constants.Constants.TOTAL_QUESTIONS;

public class DefaultQuestionService implements QuestionService {

    private final QuestionRepository questionRepository;
    private List<Integer> usedQuestionIndexes;
    private final ModelMapper modelMapper;
    private List<String> usedQuestionsTexts;

    public DefaultQuestionService(DatabaseHelper questionTableHelper) {
        questionRepository = new DefaultQuestionRepository(questionTableHelper);
        usedQuestionIndexes = new ArrayList<>();
        usedQuestionsTexts = new ArrayList<>();
        modelMapper = new ModelMapper();
    }

    public List<QuestionViewModel> getRandomQuestions() {
        int randomQuestionIndex = getRandomQuestionIndex();
        if (randomQuestionIndex == -1) {
            List<Question> questions = new ArrayList<>();
            for (int i = 1; i <= TOTAL_QUESTIONS; i++) {
                if (usedQuestionIndexes.contains(i)) {
                    continue;
                }
                usedQuestionIndexes.add(i);
                Question question = questionRepository.getQuestionById(i);
                questions.add(question);
                if (questions.size() == QUESTIONS_BUFFER_LENGTH) {
                    return questions.stream()
                            .map(q -> modelMapper.map(q, QuestionViewModel.class))
                            .collect(Collectors.toList());
                }
            }
        }
        List<Question> randomQuestions = questionRepository.getRandomQuestions(randomQuestionIndex, QUESTIONS_BUFFER_LENGTH);
        return randomQuestions.stream()
                .map(question -> modelMapper.map(question, QuestionViewModel.class))
                .collect(Collectors.toList());
    }

    private int getRandomQuestionIndex() {
        if (usedQuestionIndexes.size() == TOTAL_QUESTIONS) {
            throw new IllegalArgumentException("All questions used");
        }
        int index = RandomGenerator.generateRandomInt(1, TOTAL_QUESTIONS - QUESTIONS_BUFFER_LENGTH + 1);
        boolean isUsed = false;
        boolean seq = false;
        for (int i = 0; i < QUESTIONS_BUFFER_LENGTH; i++) {
            if (usedQuestionIndexes.contains(index + i)) {
                isUsed = true;
            }
        }
        int cnt = 0;
        while (isUsed) {
            isUsed = false;
            index = RandomGenerator.generateRandomInt(1, TOTAL_QUESTIONS - QUESTIONS_BUFFER_LENGTH + 1);
            for (int i = 0; i < QUESTIONS_BUFFER_LENGTH; i++) {
                if (usedQuestionIndexes.contains(index + i)) {
                    isUsed = true;
                }
            }
            cnt++;
            if (cnt == TOTAL_QUESTIONS) {
                seq = true;
                break;
            }
        }
        if (seq) {
            return -1;
        }

        for (int i = 0; i < QUESTIONS_BUFFER_LENGTH; i++) {
            usedQuestionIndexes.add(index + i);
        }
        return index;
    }

    @Override
    public void clearQuestionsUsed() {
        usedQuestionIndexes.clear();
    }

    public List<Question> getQuestionsWithAnswers() {
        return questionRepository.getQuestions();
    }
}

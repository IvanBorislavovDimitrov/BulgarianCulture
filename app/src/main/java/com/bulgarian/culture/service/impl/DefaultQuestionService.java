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

    public DefaultQuestionService(DatabaseHelper questionTableHelper) {
        questionRepository = new DefaultQuestionRepository(questionTableHelper);
        usedQuestionIndexes = new ArrayList<>();
        modelMapper = new ModelMapper();
    }

    @Override
    public List<QuestionViewModel> getRandomQuestion() {
        int randomQuestionIndex = getRandomQuestionIndex();
        List<Question> randomQuestions = questionRepository.getRandomQuestions(randomQuestionIndex, QUESTIONS_BUFFER_LENGTH);
        return randomQuestions.stream()
                .map(question -> modelMapper.map(question, QuestionViewModel.class))
                .collect(Collectors.toList());
    }

    private int getRandomQuestionIndex() {
        int index = RandomGenerator.generateRandomInt(0, TOTAL_QUESTIONS - QUESTIONS_BUFFER_LENGTH);
        while (usedQuestionIndexes.contains(index)) {
            index = RandomGenerator.generateRandomInt(0, TOTAL_QUESTIONS - QUESTIONS_BUFFER_LENGTH);
        }
        usedQuestionIndexes.add(index);
        return index;
    }

    public List<Question> getQuestionsWithAnswers() {
        return questionRepository.getQuestions();
    }

    public List<Question> getRandomQuestions() {
        int rand = RandomGenerator.generateRandomInt(1, questionRepository.getQuestionsCount());
        List<Question> randomQuestions = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            randomQuestions.add(questionRepository.getQuestionById(rand));
        }
        return randomQuestions;
    }
}

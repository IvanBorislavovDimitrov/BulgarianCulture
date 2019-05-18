package com.bulgarian.culture.service.impl;

import com.bulgarian.culture.constants.Constants;
import com.bulgarian.culture.database.QuestionTableHelper;
import com.bulgarian.culture.model.dto.QuestionViewModel;
import com.bulgarian.culture.model.enity.Question;
import com.bulgarian.culture.repository.api.QuestionRepository;
import com.bulgarian.culture.repository.impl.DefaultQuestionRepository;
import com.bulgarian.culture.service.api.QuestionService;
import com.bulgarian.culture.util.RandomGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.bulgarian.culture.constants.Constants.QUESTIONS_BUFFER_LENGTH;

public class DefaultQuestionService implements QuestionService {

    private final QuestionRepository questionRepository;
    private List<Question> currentQuestions;
    private int index;
    private int start;

    public DefaultQuestionService(QuestionTableHelper questionTableHelper) {
        this.questionRepository = new DefaultQuestionRepository(questionTableHelper);
    }

    @Override
    public QuestionViewModel getRandomQuestion() {
        return null;
    }

    public List<Question> getQuestionsWithAnswers() {
        return questionRepository.getQuestions();
    }

    public List<Question> getRandomQuestions(){
       int rand = RandomGenerator.generateRandomInt(1, questionRepository.getQuestionsCount());
        List<Question> randomQuestions = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            randomQuestions.add(questionRepository.getQuestionById(rand));
        }
      return randomQuestions;
    }
}

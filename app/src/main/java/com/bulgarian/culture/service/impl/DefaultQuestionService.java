package com.bulgarian.culture.service.impl;

import com.bulgarian.culture.constants.Constants;
import com.bulgarian.culture.database.QuestionTableHelper;
import com.bulgarian.culture.model.dto.QuestionViewModel;
import com.bulgarian.culture.model.enity.Question;
import com.bulgarian.culture.repository.api.QuestionRepository;
import com.bulgarian.culture.repository.impl.DefaultQuestionRepository;
import com.bulgarian.culture.service.api.QuestionService;

import java.util.List;

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
}

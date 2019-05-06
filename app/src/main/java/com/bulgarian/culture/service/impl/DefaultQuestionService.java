package com.bulgarian.culture.service.impl;

import com.bulgarian.culture.database.QuestionTableHelper;
import com.bulgarian.culture.repository.api.QuestionRepository;
import com.bulgarian.culture.repository.impl.DefaultQuestionRepository;
import com.bulgarian.culture.service.api.QuestionService;

public class DefaultQuestionService implements QuestionService {

    private final QuestionRepository questionRepository;

    public DefaultQuestionService(QuestionTableHelper questionTableHelper) {
        this.questionRepository = new DefaultQuestionRepository(questionTableHelper);
    }
}

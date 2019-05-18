package com.bulgarian.culture.repository.impl;

import com.bulgarian.culture.database.QuestionTableHelper;
import com.bulgarian.culture.model.enity.Question;
import com.bulgarian.culture.repository.api.QuestionRepository;

public class DefaultQuestionRepository implements QuestionRepository {

    private final QuestionTableHelper questionTableHelper;

    public DefaultQuestionRepository(QuestionTableHelper questionTableHelper) {
        this.questionTableHelper = questionTableHelper;
        questionTableHelper.populateQuestionsTable();
    }

    @Override
    public void save(Question question) {
        questionTableHelper.addQuestion(question);
    }

    @Override
    public Question findById(String id) {
        return null;
    }
}

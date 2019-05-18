package com.bulgarian.culture.repository.impl;

import com.bulgarian.culture.database.AnswerTableHelper;
import com.bulgarian.culture.model.enity.Answer;
import com.bulgarian.culture.repository.api.AnswerRepository;

public class DefaultAnswerRepository implements AnswerRepository {

    private AnswerTableHelper answerTableHelper;

    public DefaultAnswerRepository(AnswerTableHelper answerTableHelper) {
        this.answerTableHelper = answerTableHelper;
        answerTableHelper.populateAnswerTable();
    }

    @Override
    public void save(Answer answer) {

    }

    @Override
    public Answer findById(String id) {
        return null;
    }
}

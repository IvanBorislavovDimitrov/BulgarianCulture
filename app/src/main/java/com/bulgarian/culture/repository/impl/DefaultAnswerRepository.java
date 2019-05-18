package com.bulgarian.culture.repository.impl;

import com.bulgarian.culture.database.DatabaseHelper;
import com.bulgarian.culture.model.enity.Answer;
import com.bulgarian.culture.repository.api.AnswerRepository;

public class DefaultAnswerRepository implements AnswerRepository {

    private DatabaseHelper databaseHelper;

    public DefaultAnswerRepository(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
        databaseHelper.populateAnswerTable();
    }

    @Override
    public void save(Answer answer) {

    }

    @Override
    public Answer findById(String id) {
        return null;
    }
}

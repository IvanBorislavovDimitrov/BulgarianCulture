package com.bulgarian.culture.service.impl;

import com.bulgarian.culture.database.DatabaseHelper;
import com.bulgarian.culture.repository.api.AnswerRepository;
import com.bulgarian.culture.repository.impl.DefaultAnswerRepository;
import com.bulgarian.culture.service.api.AnswerService;

public class DefaultAnswerService implements AnswerService {

    private AnswerRepository answerRepository;

    public DefaultAnswerService(DatabaseHelper databaseHelper) {
        this.answerRepository = new DefaultAnswerRepository(databaseHelper);
    }
}

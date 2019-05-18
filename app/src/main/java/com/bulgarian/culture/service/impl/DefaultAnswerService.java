package com.bulgarian.culture.service.impl;

import com.bulgarian.culture.database.AnswerTableHelper;
import com.bulgarian.culture.repository.api.AnswerRepository;
import com.bulgarian.culture.repository.impl.DefaultAnswerRepository;
import com.bulgarian.culture.service.api.AnswerService;

public class DefaultAnswerService implements AnswerService {

    private AnswerRepository answerRepository;

    public DefaultAnswerService(AnswerTableHelper answerTableHelper) {
        this.answerRepository = new DefaultAnswerRepository(answerTableHelper);
    }
}
